package Project;

import java.util.Comparator;

//test:1
class SortCategoriesByName implements Comparator<category> {

	@Override
	public int compare(category c1, category c2) {

		return c1.getName().compareTo(c2.getName());
	}

}

//test:1
class SortGroupsByName implements Comparator<Group> {

	@Override
	public int compare(Group g1, Group g2) {

		return g1.getGroupName().compareTo(g2.getGroupName());
	}

}

//test:1
class SortUsersByName implements Comparator<User> {

	@Override
	public int compare(User u1, User u2) {

		return u1.getName().compareTo(u2.getName());
	}

}

//test:1
class SortUsersByUsername implements Comparator<User> {
	
	@Override
	public int compare(User u1, User u2) {
		
		return u1.getId().toUpperCase().compareTo(u2.getId().toUpperCase());
		
//		return u1.getId().compareTo(u2.getId());
	}
	
}