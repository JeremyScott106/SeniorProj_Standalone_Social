package Project;

import java.util.Comparator;

class SortCategoriesByName implements Comparator<category> {

	@Override
	public int compare(category c1, category c2) {

		return c1.getName().compareTo(c2.getName());
	}

}


class SortGroupsByName implements Comparator<Group> {

	@Override
	public int compare(Group g1, Group g2) {

		return g1.getGroupName().compareTo(g2.getGroupName());
	}

}


class SortUsersByName implements Comparator<User> {

	@Override
	public int compare(User u1, User u2) {

		return u1.getName().compareTo(u2.getName());
	}

}