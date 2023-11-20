package Project;

public class Voted {
	
	private boolean hasUpvoted;
	private boolean hasDownvoted;
	private User user;
	private Post post;
	
	public Voted (User u, Post p){
		this.user = u;
		this.post = p;
		hasUpvoted = false;
		hasDownvoted = false;
	}
	
	public void up() {
		hasUpvoted = true;
		hasDownvoted = false;
	}
	
	public void down() {
		hasUpvoted = false;
		hasDownvoted = true;
	}
	
	public boolean getUp(){
		return hasUpvoted;
	}
	
	public boolean getDown(){
		return hasDownvoted;
	}
	
	public User getUser(){
		return user;
	}
	
	public Post getPost(){
		return post;
	}
	
	public boolean compareTo(Voted v) {
		if (v.getUser().compareId(user.getId())) {
			if(v.getPost().compareTo(post) == 1) {
				return true;
			}
		}
		else {
			return false;
		}
		return false;
	}
	
}
