package beans;

import java.io.Serializable;
import java.sql.*;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;

public class SectorPKGenerator extends IdentityGenerator {
String name;
@Override
public Serializable generate(SessionImplementor s, Object obj) {
	try{
		name = "SEC";
		Connection con = s.connection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select sectorGenerator.nextval from dual");
		if(rs.next()){
			name = name + rs.getInt(1);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return name;
}
}
