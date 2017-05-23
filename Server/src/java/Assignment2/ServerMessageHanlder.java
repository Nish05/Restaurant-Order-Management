/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import org.w3c.dom.NodeList;

/**
 *
 * @author Nish
 */
public class ServerMessageHanlder implements SOAPHandler<SOAPMessageContext> {
    
    Map<String,ArrayList<String>> clientInfo = new HashMap<>();
    
    public boolean handleMessage(SOAPMessageContext messageContext) {
        String outProperty = SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY; 
        boolean outgoing = (Boolean)messageContext.get(outProperty);
        SOAPMessage msg = messageContext.getMessage();
        String username="";
        String opId="";
        try {
                SOAPHeader header=msg.getSOAPHeader(); 
                Iterator it=header.examineAllHeaderElements(); 
                while(it.hasNext()){
                    
                    SOAPHeaderElement e=(SOAPHeaderElement) it.next(); 
                    NodeList seq = e.getElementsByTagName("resto:Operation");
                    
                    if(!checkContent(seq)){
                        SOAPBody soapBody = messageContext.getMessage().getSOAPPart().getEnvelope().getBody();
                        SOAPFault soapFault = soapBody.addFault();
                        soapFault.setFaultString("Invalid Sequence Number");
                        throw new SOAPFaultException(soapFault);
                    } 
                    
                    
                }
//                File Username = new File("username.txt");
//                Username.createNewFile();
//                FileOutputStream f=new FileOutputStream(Username); 
//                byte data[]=username.getBytes();
//                f.write(data);
//                try {
//                    File InputFile = new File("inputMessage.txt");
//                    InputFile.createNewFile(); // if file already exists will do nothing 
//                    msg.writeTo(new FileOutputStream(InputFile));
//                } catch (SOAPException ex) {
//                    Logger.getLogger(ServerMessageHanlder.class.getName()).log(Level.SEVERE, null, ex);
//                }
           
        } catch(SOAPException e) {

            System.out.println("SOAP IO Error!!!!"); 
            throw new RuntimeException(e);
        }
        return true;
    }
    
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }
    
    public boolean handleFault(SOAPMessageContext messageContext) {

         
        return true;
    }
    
    public void close(MessageContext context) {
    }
    public boolean checkContent(NodeList seq){
        
        String username = seq.item(0).getTextContent();
        String opId = seq.item(1).getTextContent();
        
        checkDone(username);
        ArrayList<String> operationSeq;
        Set<String> keySet = clientInfo.keySet();
        
        if(keySet.contains(username)){
            operationSeq = clientInfo.get(username);
            
            if(Integer.parseInt(opId)-1 == operationSeq.size()){
                if(Integer.parseInt(opId) == 2 && operationSeq.get(0).equals("1") || 
                        Integer.parseInt(opId) == 1 && operationSeq.isEmpty()){
                    operationSeq.add(opId);
                    clientInfo.put(opId, operationSeq);
                    return true;
                }
            }
            
            
        } else if(opId.equals("1")){
            operationSeq = new ArrayList<>();
            operationSeq.add(opId);
            clientInfo.put(username, operationSeq);
            return true;
        }
        return false;
    }

    public void checkDone(String username) {
        
        Set<String> keySet = clientInfo.keySet();
        if(keySet.contains(username)){
            ArrayList<String> operations = clientInfo.get(username);
            if(operations.size() == 2 ){
                clientInfo.remove(username);
            }
        }
    }
}
  