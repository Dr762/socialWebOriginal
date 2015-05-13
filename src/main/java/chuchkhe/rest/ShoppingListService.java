/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chuchkhe.rest;

import chuchkhe.domain.DAO;
import chuchkhe.domain.TestDataIns;
import chuchkhe.entity.CustomerOrder;
import chuchkhe.entity.Employee;
import chuchkhe.entity.Item;
import chuchkhe.entity.Person;
import chuchkhe.entity.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author alex
 */
@Path("getShopList")
public class ShoppingListService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ShoppingListService
     */
    public ShoppingListService() {
    }

    /**
     * Retrieves representation of an instance of
     * chuchkhe.rest.ShoppingListService
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public CustomerOrder getXml(@QueryParam("username") String username) {

        System.out.println("user: " + username);
        CustomerOrder co = DAO.getAssignedCO(username);
        if (co == null) {
            co = new CustomerOrder();

        }
        LOG.severe("sent List");
        return co;

    }

    @GET
    @Path("setStatus")
    public String setEmployeeStat(@QueryParam("username") String username, @QueryParam("isOn") boolean isOn) {
        Boolean isOk = DAO.changeEmployeeStat(username, isOn ? Status.ST_Online : Status.ST_Offline);

        return isOk ? "Ok" : "Not Changed";
    }

    @GET
    @Path("setActive")
    public String setActiveOrder(@QueryParam("username") String username, @QueryParam("custOrderID") Long custOrderID) {
        Boolean isOk = DAO.changeOrderStat(custOrderID, Status.ST_Active);

        return isOk ? "Ok" : "Not Changed";
    }

    /**
     * PUT method for updating or creating an instance of ShoppingListService
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Path("{username}")
    @Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM})
    public String postXml(@PathParam("username") String username,
            CustomerOrder //                          String      
                    co) {
        LOG.severe(username);
        for (Item it : co.getItems()) {
            it.setOrder(co);
            LOG.severe(it.toString());
        }
        if (DAO.updateItems(co.getItems())) {
            DAO.changeOrderStat(co.getId(), Status.ST_Closed);

        }
        LOG.severe("" + co);
        // DAO.saveOrder(co);
        return "Ok!";
    }
    private static final Logger LOG = Logger.getLogger(ShoppingListService.class.getName());

    @GET
    @Path("insertData")
    public String insertData() {
        TestDataIns data = new TestDataIns();
        data.createEmployee();
        data.createCustomer();
        data.dispatchEmployee();
        
     
        data.fillStatus();
        data.fillSTree();
           data.createOrder();
        return "Ok";
    }
    
    @GET
    @Path("testAvail")
    public String testAvail(){
        TestDataIns dt = new TestDataIns();
        dt.dispatchEmployee();
        return "ok";
    }
    
    
    @GET
    @Path("showHistory")
    @Produces({MediaType.TEXT_PLAIN+ ";charset=UTF-8"})
    public String getHistory(){
       TestDataIns dt = new TestDataIns();
       StringBuffer sb = new StringBuffer();
       for(Object[] o : (List<Object[]>) dt.showHistory()){
           for(int i = 0 ; i < o.length; i++)
               sb.append(o[i] + " ");
           sb.append("\n");
       }
       return sb.toString();
    }
    
    
      @GET
    @Path("showUsers")
    @Produces({MediaType.TEXT_PLAIN+ ";charset=UTF-8"})
    public String getUsers(){
       TestDataIns dt = new TestDataIns();
       StringBuffer sb = new StringBuffer();
       for(String  s : (List<String>) dt.showUsers()){
           
               sb.append(s + " ");
           sb.append("\n");
       }
       return sb.toString();
    }
    
    
}
