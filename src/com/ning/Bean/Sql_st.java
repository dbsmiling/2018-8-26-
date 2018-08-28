package com.ning.Bean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ning.dao.Dao;
import com.ning.dao.Daoimp;

public class Sql_st {
	Dao getdao=new Daoimp();
// 通过商品CNME查询商品
	public List<Map> ComQuery(String cname) {
		List<Map> list=new ArrayList<>();
		String sql = "SELECT * FROM Commodity WHERE cname=?";
		String obj[]= {cname==null?"":cname};
		return (List<Map>) getdao.commodityQuery(sql, obj);
	}
//	通过商品ID查询商品
	public List<Map> DCQuery(String id){
		List<Map> list=new ArrayList<>();
		String sql = "SELECT * FROM Commodity WHERE cid=?";
		String obj[]= {id==null?"":id};
		return (List<Map>) getdao.commodityQuery(sql, obj);
	}
	
//	通过商品分类分页查询
	public List<Map> Cpagequery(String CAbb,int page) {
		String sql ="SELECT * FROM Commodity WHERE CAbb=? limit "+page+",20";
		String obj[]= {CAbb==null?"":CAbb};
		return (List<Map>) getdao.commodityQuery(sql, obj);
	}
	public List<Map> Cpagequery(String CAbb) {
		String sql ="SELECT * FROM Commodity WHERE CAbb=?";
		String obj[]= {CAbb==null?"":CAbb};
		return (List<Map>) getdao.commodityQuery(sql, obj);
	}
	
//	通过id查询详情页面的图片
	public List CIMGQuery(String id){
		List<Map> list=new ArrayList<>();
		String sql = "SELECT * FROM comimg WHERE id=?";
		String obj[]= {id==null?"":id};
		return (List) getdao.CIMGQuery(sql, obj);
	}
	
// 客户ID查询	
	public String SPCBquery(String email) {
		String sql = "SELECT Cid FROM Customer WHERE Cemail=?";
		String obj[]= {email==null?"":email};
		return getdao.Query_dt(sql, obj);
	}
//  客户名查询
	public String Uesrquery(String email) {
		String sql = "SELECT Cname FROM Customer WHERE Cemail=?";
		String obj[]= {email==null?"":email};
		return getdao.Query_dt(sql, obj);
	}
	
//	购物车数据添加
	public int SPCBinter(String SCID, String sc_id,String Snum) {
		String sql = "insert into Shop_cart(Snum,SCID,sc_id)values(?,?,?)";
		String obj[]= {Snum==null?"":Snum,SCID==null?"":SCID,sc_id==null?"":sc_id};
		return getdao.update(sql, obj);
		
	}
//	 购物车信息条数查询
	public int CNquery(String SCID) {
		String sql = "SELECT * FROM Shop_cart WHERE SCID=?";
		String obj[]= {SCID==null?"":SCID};
		return getdao.CNquery(sql, obj);
	}
	 
// 	抢购表信息获取
	public List<?> Rushcomquery(String Rbatch) {
		String sql = "SELECT * FROM Rush_com WHERE Rbatch=?";
		String obj[]= {Rbatch==null?"":Rbatch};
		return getdao.Rushcomquery(sql, obj);
	}
// 	抢购表信息修改	
	public int Rushupdate(String Rcnum,String Rcid) {
		String sql ="UPDATE Rush_com SET Rcnum=? WHERE Rcid=?";
		String obj[]= {Rcnum==null?"":Rcnum,Rcid==null?"":Rcid};
		return getdao.update(sql, obj);	
	}

	public List<?> Rushquery(String Rcid) {
		String sql ="SELECT * FROM Rush_com WHERE Rcid=?";
		String obj[]= {Rcid==null?"":Rcid};
		return getdao.Rushcomquery(sql, obj);
	}
	
	private static Sql_st Sql_st;
	public static synchronized Sql_st getSql_st() {
		if(Sql_st==null) {
			Sql_st= new Sql_st();
			return Sql_st;
		}else {
			return Sql_st;
		}
	}
	private Sql_st() {}
}
