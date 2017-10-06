/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;
import classes.Dado;

/**
 *
 * @author Elias Rodelo
 */
public class ServletDados extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Map data = request.getParameterMap();
            Map data2 = new HashMap();
            data2.putAll(data);
            out.println(getResultados(data2));
        }
    }
    
    private String getResultados(Map data){
        data = getWinners(data);
        data = duplicaOTriplica(data);
        String resultado = "";
        for(int i = 1; i <= 4; i++){
            if(data.containsKey("ganador" + i)){
                String nombre = removeBrackets(Arrays.toString((String[])data.get("jugador"+i)));
                Object apuesta = data.get("apJugador"+i);
                resultado += nombre + " ganó, y su nuevo saldo es " + apuesta + "<br>";
            }
        }
        
        if(resultado.isEmpty()){
            resultado = "Vuelva a intentarlo, no hubo ningún ganador";
        }
        
        return resultado;
    }
    
    private Map getWinners(Map data){
        
        int[] dadoJugadores = new int [4];
        
        dadoJugadores[0] = Integer.parseInt(removeBrackets(Arrays.toString((String[])data.get("dadoJugador1"))));
        dadoJugadores[1] = Integer.parseInt(removeBrackets(Arrays.toString((String[])data.get("dadoJugador2"))));
        dadoJugadores[2] = Integer.parseInt(removeBrackets(Arrays.toString((String[])data.get("dadoJugador3"))));
        dadoJugadores[3] = Integer.parseInt(removeBrackets(Arrays.toString((String[])data.get("dadoJugador4"))));
        
        int i = 0;
        int dado = tiraDados();
        
        for(int dadoJugador : dadoJugadores){
            if(dadoJugador == dado){
                
                data.put("ganador" + (i+1), "true");
                Object ob = new Object();
                ob = dado;
                data.put("dado" + (i+1), dado);
            }
            i++;
        }
        
        return data;
        
    }
    
    private Map duplicaOTriplica(Map data) {
        
        for(int i = 1; i <= 4; i++){
                if(data.containsKey("ganador" + i)){
               Object ob = data.get("dado" + i);
                int dado = Integer.parseInt(ob.toString());
                if( dado % 2 == 0 ){
                    int apuesta = Integer.parseInt(removeBrackets(Arrays.toString((String[])data.get("apJugador" + i))));
                    String apuestag = "" + (apuesta * 2);
                    data.replace("apJugador" + i, "" + apuesta, apuestag);
                } else {
                    int apuesta = Integer.parseInt(removeBrackets(Arrays.toString((String[])data.get("apJugador" + i))));
                    String apuestag = "" + (apuesta * 3);
                    data.replace("apJugador" + i, apuestag);
                }
            }
        }
        
        return data;
    }
    
    private int tiraDados() {
        Dado dado = new Dado(10);
        return dado.getRandom();
    }
    
    
    private String removeBrackets(String str){
        String tempStr = "";
        String[] str2 = str.split("");
        for(int i = 0; i < str.length(); i++){
            if(!(str2[i].equals("[") | str2[i].equals("]"))){
                tempStr = tempStr.concat(str2[i]);
            }
        }
        return tempStr;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
