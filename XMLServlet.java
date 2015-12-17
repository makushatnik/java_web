package com.cdesign.bookstore.servlets;

import java.io.*;
import java.util.*;
import java.net.URL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.cdesign.bookstore.model.*;

/**
 * @version 1.0
 * @author Ageev Evgeny
 */
public class XMLServlet extends HttpServlet {
    
    private DocumentBuilderFactory dbFactory;
    private TransformerFactory tFactory;
    private String XSLFileName;
    private List<ClientModel> clientList;
    
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        setXSLFileName(config.getInitParameter("XSL_FILE"));
        dbFactory = DocumentBuilderFactory.newInstance();
        tFactory = TransformerFactory.newInstance();
        tFactory.setURIResolver(new URIResolver() {
            public Source resolve(String href, String base)
            {
                try {
                    ServletContext cont = getServletContext();
                    URL url = cont.getResource(href);
                    return new StreamSource(url.openStream());
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
        
        clientList = buildClientList();
    }
    //получение DocumentBuilder для построения XML
    public DocumentBuilder getDocumentBuilder(boolean validating)
    {
        try {
            dbFactory.setValidating(validating);
            return dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }
    //получение синтаксического анализатора не проверяющего на допустимость
    public DocumentBuilder getDocumentBuilder()
    {
        return getDocumentBuilder(false);
    }
    
    public void setXSLFileName(String fname)
    {
        XSLFileName = fname;
    }
    
    public String getXSLFileName()
    {
        return XSLFileName;
    }
    
    public void writeXML(HttpServletRequest request, HttpServletResponse response,
            Document doc) throws IOException
    {
        HttpSession session = request.getSession(true);
        ClientModel client = (ClientModel)session.getAttribute("clientModel");
        if (client == null)
        {
            String userAgent = request.getHeader("User-Agent");
            client = getClientModel(userAgent);
            session.setAttribute("clientModel", client);
        }
        response.setContentType(client.getContentType());
        PrintWriter out = response.getWriter();
        String xslFile = client.getXSLPath() + getXSLFileName();
        
        InputStream xslStream = getServletContext().getResourceAsStream(xslFile);
        transform(doc, xslStream, out);
        out.close();
    }
    
    public void transform(Document doc, InputStream xsl, PrintWriter out)
    {
        try {
            Source xmlSource = new DOMSource(doc);
            Source xslSource = new StreamSource(xsl);
            Result res = new StreamResult(out);
            Transformer trans = tFactory.newTransformer(xslSource);
            trans.transform(xmlSource, res);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
    
    public Node buildErrorMessage(Document doc, String msg)
    {
        Element error = doc.createElement("error");
        Element errorMsg = doc.createElement("message");
        errorMsg.appendChild(doc.createTextNode(msg));
        error.appendChild(errorMsg);
        return error;
    }
    
    private List buildClientList()
    {
        DocumentBuilder builder = getDocumentBuilder(true);
        List<ClientModel> cList = new ArrayList<>();
        
        String clientXML = getServletContext().getInitParameter("CLIENT_LIST");
        try {
            InputStream clientXMLStream = getServletContext().getResourceAsStream(clientXML);
            Document clientsDoc = builder.parse(clientXMLStream);
            NodeList clientElements = clientsDoc.getElementsByTagName("client");
            int listLen = clientElements.getLength();
            for (int i = 0; i < listLen; i++)
            {
                Element client = (Element)clientElements.item(i);
                Element agentEl = (Element)client.getElementsByTagName("userAgent").item(0);
                Text agentText = (Text)agentEl.getFirstChild();
                String agent = agentText.getNodeValue();
                
                Element typeEl = (Element)client.getElementsByTagName("contentType").item(0);
                Text typeText = (Text)typeEl.getFirstChild();
                String type = typeText.getNodeValue();
                
                Element pathEl = (Element)client.getElementsByTagName("XSLPath").item(0);
                Text pathText = (Text)pathEl.getFirstChild();
                String path = pathText.getNodeValue();
                
                cList.add(new ClientModel(agent, type, path));
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cList;
    }
    
    private ClientModel getClientModel(String header)
    {
        for (ClientModel c : clientList)
        {
            if (header.indexOf(c.getUserAgent()) > -1)
                return c;
        }
        return new ClientModel("DEFAULT CLIENT", "text/html", "/XHTML/");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
