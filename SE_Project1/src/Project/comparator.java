package Project;

import java.util.Comparator;

class SortCategoriesByName implements Comparator<category> {

	public int compare(category c1, category c2) {
		
		return c1.getName().compareTo(c2.getName());
	}

}


class SortGroupsByName implements Comparator<group> {

	public int compare(group g1, group g2) {
		
		return g1.getName().compareTo(g2.getName());
	}	
	
}
