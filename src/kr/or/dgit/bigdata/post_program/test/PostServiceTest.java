package kr.or.dgit.bigdata.post_program.test;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.bigdata.post_program.dto.Post;
import kr.or.dgit.bigdata.post_program.service.PostService;

public class PostServiceTest {
	private static PostService postService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		postService = new PostService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		postService = null;
	}

//	@Test
//	public void testSelectSido() {
//		List<Post> list = postService.selectSido();
//		
//		Assert.assertNotNull(list);
//		
//		for (Post p : list) {
//			System.out.println(p);
//		}
//	}

	@Test
	public void testSearchDoro(){
		Post post = new Post();
		post.setSido("강원도");
		post.setDoro("임곡로");
		
		List<Post> list = postService.searchDoro(post);
		
		Assert.assertNotNull(list);
		
		for (Post p : list) {
			System.out.println(Arrays.toString(p.toArray()));
		}
	}
}
