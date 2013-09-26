package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import models.Project;

public class DAODotProject implements DAOGeneric {	
	
	private java.sql.Connection conexao;

	private void connect() throws SQLException{
			conexao = DaoFactory.getConnection();
	}

	
	public int countProjectsInProgress() {
		int c = 0;
		try{
			connect();
			PreparedStatement pst = conexao.prepareStatement("select count(project_id) from dotp_projects where project_status = ?");
			pst.setString(1, "3");
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				c =  rs.getInt(1);
			conexao.close();
		}	catch (SQLException e){ e.printStackTrace();}
		return c;
	}
	
	public Object[] getProjectsInProgress() {
		List<Integer>  l = new LinkedList<Integer>();
		try{
			connect();
			PreparedStatement pst = conexao.prepareStatement("select project_id from dotp_projects where project_status = ?");
			pst.setString(1, "3");
			ResultSet rs = pst.executeQuery();
			while (rs.next())						
				l.add(rs.getInt(1));
			conexao.close();
		}	catch (SQLException e){ e.printStackTrace();}
		return l.toArray();
	}
	
	public Project getProject(int id) {
		Project p = new Project();
		try{
			connect();
			PreparedStatement pst = conexao.prepareStatement("select datediff(project_end_date, project_start_date) as timeProj, project_start_date as sd, project_end_date as ed, project_target_budget as b from dotp_projects where project_id = ?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()){
				if (rs.getInt(1) != 0){											//garantir que a data seja válida (NÃO NULA)
					p.setStartDate(rs.getDate("sd"));
					p.setEndDate(rs.getDate("ed"));					
					p.setTime(rs.getInt("timeProj"));
				}
				p.setBudget(rs.getDouble("b"));
			}
			conexao.close();
		}	catch (SQLException e){ e.printStackTrace();}
		return p;
	}
	
}
