package Project;

import java.util.Comparator;

class SortCategoriesByName implements Comparator<category> {

	@Override
	public int compare(category c1, category c2) {

		return c1.getName().toUpperCase().compareTo(c2.getName().toUpperCase());
	}

}


class SortGroupsByName implements Comparator<Group> {

	@Override
	public int compare(Group g1, Group g2) {

		return g1.getGroupName().toUpperCase().compareTo(g2.getGroupName().toUpperCase());
	}

}


class SortUsersByName implements Comparator<User> {

	@Override
	public int compare(User u1, User u2) {

		return u1.getName().compareTo(u2.getName());
	}

}


class SortUsersByUsername implements Comparator<User> {
	
	@Override
	public int compare(User u1, User u2) {
		
		return u1.getId().toUpperCase().compareTo(u2.getId().toUpperCase());
		
//		return u1.getId().compareTo(u2.getId());
	}
	
}

class SortPostsByDate implements Comparator<Post> {
	@Override
	public int compare(Post p1, Post p2) {
		return p2.getTime().compareTo(p1.getTime());
	}
}

//class SortResponsesByDate implements Comparator<Response> {
//	@Override
//	public int compare(Response r1, Response r2) {
//		return r2.getTime().compareTo(r1.getTime());
//	}
//}