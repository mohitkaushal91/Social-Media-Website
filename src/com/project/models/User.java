package com.project.models;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.project.db.PostDBUtil;
import com.project.db.UserDBUtil;

public class User {

	String email;
	String name;
	String pass;
	ArrayList<Post> posts  =  new ArrayList<>();
	
	ArrayList<Integer> likedpostsID = new ArrayList<>();
	
	ArrayList<Post> userPosts  =  new ArrayList<>();
	String post;
	String inputtext;
	int userID;
	int postID;
	Boolean matched;
	
	
	public User(String email, String name, String pass) {

		this.email = email;
		this.name = name;
		this.pass = pass;
	}
	
	public User(String email, String pass) {
		this.email = email;
		this.pass = pass;
	}
	
	public User(String email,String name,int userID) {
		this.email=email;
		this.name=name;
		this.userID=userID;
	}
	
	public User(int userId, String email,String post) {
		this.userID = userId;
		this.email = email;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	
	public User(String inputtext) {
	this.inputtext=inputtext;	
	}
	
	public int getUserId() {
		return userID;
	}
	public int setUserId(int userID) {
		this.userID=userID;
		return userID;
	}
	
	
	public int getPostId() {
		return this.postID;
	}
	
	public void setPostId(int postID) {
		this.postID=postID;
	}
	
	
	public String getEmail() {
		return email;	
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return this.pass;			
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public boolean getMatch()
	{
		return this.matched;
	}
	public void setMatch(boolean match)
	{
		this.matched = match;
	}
	
	public ArrayList<Post> getPosts()
	{
		return this.posts;
	}
	
	public String getPost()
	{
		return this.post;
	}
	public void setPost(String post)
	{
		this.post = post;
	}
	
	
	public void setPosts(Post post)
	{
		this.posts.add(post);
	}
	
	public void setUserPosts(Post post)
	{
		this.userPosts.add(post);
	}
	public ArrayList<Post> getUserPosts()
	{
		return this.userPosts;
	}
	
	public void register(UserDBUtil userdb) {
		
		try {
			
			System.out.println("we are in regsiter funct in user");
			userdb.uploaddata(this);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void login(UserDBUtil userdb) {
		try {
			User dtemp = userdb.getUserDetails(this.email);
			
			if(this.pass.equals(dtemp.getPass()))
			{
				this.name = dtemp.getName();
				this.email = dtemp.getEmail();
				
				this.setMatch(true);								
			}	
			else
			{
				this.setMatch(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void post(String post ,PostDBUtil postdb)
	{
		System.out.println("user post");
		try {
			
			System.out.println(this.email);
			System.out.println(this.post);
			postdb.DoPost(new Post(this.email,post));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	
	public void DisplayAllPosts(PostDBUtil postdb) {
		
		try {
			likedpostsID.clear();
			postdb.DoGet(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void GetUserPost(PostDBUtil postdb) {
		
		try {
			userPosts.clear();
			postdb.DoGetOnlyUserPosts(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void deletePost(PostDBUtil postdb) {
		try {
			userPosts.clear();
			postdb.deletePost(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void Editpost(PostDBUtil postdb)
	{
		try {
			userPosts.clear();
			postdb.UpdateEditPost(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void insertLike(PostDBUtil postdb)
	{
		try {
			posts.clear();
			postdb.insertLikes(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void deleteLike(PostDBUtil postdb)
	{
		try {
			posts.clear();
			postdb.deleteLikes(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void setLikedPostInSession(int postid)
	{
		likedpostsID.add(postid);
	}
	
	public ArrayList<Integer> getLikedPostInSession()
	{
		return this.likedpostsID;
	}
	
}
