package service;

import java.io.File;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import DAO.PODAO;
import bean.Address;
import bean.Item;
import bean.PO;
import bean.POReportWrapper;
import bean.POWrapper;

@Path("/OrderService")
public class POInfoRest {

	private final static POInfo  POINFO = new POInfo();
	@Context
	private ServletContext context; 

	
	@GET
	@Path("/Order/{bid}")
	@Produces("text/xml")
	public String getOrdersByBid(@PathParam("bid") String bid) throws Exception{
		List<POWrapper> pol = POINFO.getOrdersByBid(bid);
		POReportWrapper por = new POReportWrapper(pol);
		String separator = File.separator;
		String f = "export" + separator;
		String filename = context.getRealPath(separator + f);
		String path = filename.substring(0, filename.lastIndexOf(separator));
		JAXBContext jc = JAXBContext.newInstance(por.getClass());
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
	    SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	    String fname = path + separator + f + "po.xsd";
	    Schema schema = sf.newSchema(new File(fname));
	   
	    marshaller.setSchema(schema);
	    
		StringWriter sw = new StringWriter();
		sw.write("<?xml version='1.0'?>\n");
	    //sw.write("<?xml-stylesheet type=\"text/xsl\" href=\"SIS.xsl\"?>");
	    //sw.write("\n");
	    marshaller.marshal(por, new StreamResult(sw));
		//System.out.println(sw.toString());
		return sw.toString();
		
	}



}
