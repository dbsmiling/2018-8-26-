package com.ning.Serive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.w3c.dom.ls.LSInput;

import com.ning.Bean.Sql_st;

public class Seriveimp implements Serive{

	@Override
	public int SPCBserive(String email, String sc_id, String Snum) {
		String SCID = Sql_st.getSql_st().SPCBquery(email);
		return Sql_st.getSql_st().SPCBinter(SCID, sc_id, Snum);
	}
	@Override
	public int CNquery(String email) {
		String SCID = Sql_st.getSql_st().SPCBquery(email);
		return Sql_st.getSql_st().CNquery(SCID);
	}
	@Override
	public String Rushserive(String Rcnum, String num) {
		
		int numtmp = Integer.valueOf(num) ;
		int a = Integer.valueOf(Rcnum);
		double percent = (a*100)/numtmp;
		String per = (percent)+"%";
		return per;
	}
	@Override
	public int PNumquery(String CAbb) {
		List<Map> list = new ArrayList<>();
		list=Sql_st.getSql_st().Cpagequery(CAbb);
		int i = list.size();
		return i;
	}

}
