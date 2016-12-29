package kr.or.dgit.bigdata.post_program.dao;

import java.util.List;

import kr.or.dgit.bigdata.post_program.dto.Post;

public interface PostDao {
	List<Post> selectSido();
	List<Post> searchDoro(Post post);
}
