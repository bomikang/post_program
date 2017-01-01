package kr.or.dgit.bigdata.post_program.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.border.EmptyBorder;

import kr.or.dgit.bigdata.post_program.dao.PostDao;
import kr.or.dgit.bigdata.post_program.dto.Post;
import kr.or.dgit.bigdata.post_program.setting.ConnectionFactory;
import kr.or.dgit.bigdata.post_program.setting.JdbcUtil;

public class PostService implements PostDao {
	private static final PostService instance = new PostService();
	
	public static PostService getInstance() {
		return instance;
	}
	
	public PostService(){}

	@Override
	public List<Post> selectSido() {
		String sql = "select distinct sido from post";
		
		Connection con = ConnectionFactory.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Post> list = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			//rs.next()가 존재하지 않을 경우 비어있는 리스트 리턴
			if (!rs.next()){
				return Collections.emptyList(); 
			}
			
			list = new ArrayList<>();
			
			do {
				Post sido = new Post(rs.getString("sido"));
				list.add(sido);
			} while (rs.next());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt, rs);
			JdbcUtil.close(con);
		}
		return list;
	}

	@Override
	public List<Post> searchDoro(Post post) {
		String sql = "select * from post where sido=? and doro=?";
		
		Connection con = ConnectionFactory.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Post> list = new ArrayList<>();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, post.getSido());
			pstmt.setString(2, post.getDoro());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(getPost(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private Post getPost(ResultSet rs) throws SQLException {
		String zipcode = rs.getString("zipcode");
		String sido = rs.getString("sido");
		String sigungu = rs.getString("sigungu");
		String doro = rs.getString("doro");
		int building1 = rs.getInt("building1");
		int building2 = rs.getInt("building2");
		
		return new Post(zipcode, sido, sigungu, doro, building1, building2);
	}

	
}
