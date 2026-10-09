/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.recursos.human.reclutamiento.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.com.recursos.human.reclutamiento.config.DataBaseConnection;
import main.java.com.recursos.human.reclutamiento.dto.reponse.LoginDTOResponse;
import main.java.com.recursos.human.reclutamiento.dto.request.LoginDTORequest;

/**
 *
 * @author informatica
 */
public class AuthRepository {
    public LoginDTOResponse findUserbyEmail(LoginDTORequest loginDTORequest){
        String sql = "select u.nombre, u.apellido, u.contrasena, r.id_rol " +
                     "from usuarios AS u " +
                     "inner join roles as r " +
                     "on u.id_rol = r.id_rol " +
                     "where u.correo = ?";
        try (PreparedStatement pstm = DataBaseConnection.getDataBaseConnection().prepareStatement(sql)) {
            pstm.setString(1, loginDTORequest.getEmail());
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return new LoginDTOResponse(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("contrasena"),
                        rs.getString("id_rol") 
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar al usuario: " + e.getMessage());
        }
        return null;
    }
}