package beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;

public class TeamPKGenerator extends IdentityGenerator {
	String name;
	
	@Override
	public Serializable generate(SessionImplementor s, Object obj) {
		try{
			name = "TEAM";
			java.sql.Connection con = s.connection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select teamGenerator.nextval from dual");
			if(rs.next()){
				name = name + rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return name;
	}
}
