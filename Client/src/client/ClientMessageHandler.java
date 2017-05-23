/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPElement; 
import javax.xml.soap.SOAPEnvelope; 
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.ws.soap.SOAPFaultException;
/**
 *
 * @author Nish
 */
public class ClientMessageHandler implements SOAPHandler<SOAPMessageContext> {
    
    public boolean handleMessage(SOAPMessageContext messageContext) {
        Boolean outboundProperty = (Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outboundProperty.booleanValue()) {
            
            //if the message is outgoing one, i.e., the request to a web service, we add account information into the header
            
            try {
                SOAPMessage message = messageContext.getMessage(); 
                SOAPEnvelope envelope = messageContext.getMessage().getSOAPPart().getEnvelope(); 
                SOAPHeader header=envelope.getHeader();

                if (header==null){
                    header = envelope.addHeader(); 
                }
//                SOAPElement usernameToken=header.addChildElement("UsernameToken","wsse", "http://docs.oasis- open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
//                SOAPElement username=usernameToken.addChildElement("Username","wsse"); 
//                username.addTextNode("myUserName");
//                String opNum ="1";
                SOAPElement operationToken = header.addChildElement("OpToken", "resto","http://RestuarantOrder");
                SOAPElement operation = operationToken.addChildElement("Operation","resto");
                operation.addTextNode(Client.getName());
                SOAPElement operation1 = operationToken.addChildElement("Operation","resto");
                operation1.addTextNode(Client.getopNum());
                message.saveChanges();
                
            } catch (SOAPFaultException e) { 
                System.err.println(e);
            } catch (SOAPException ex) {
                System.err.println(ex);
            }
        } 
        
        return outboundProperty;
    }
    
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }
    
    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }
    
    public void close(MessageContext context) {
    }
    
}
