package it.polito.tdp.borders.model;

import java.util.Objects;

public class Border {
private int id1;
private int id2;
public Border(int id1, int id2) {
	super();
	this.id1 = id1;
	this.id2 = id2;
}
public int getId1() {
	return id1;
}
public void setId1(int id1) {
	this.id1 = id1;
}
public int getId2() {
	return id2;
}
public void setId2(int id2) {
	this.id2 = id2;
}
@Override
public int hashCode() {
	return Objects.hash(id1, id2);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Border other = (Border) obj;
	return id1 == other.id1 && id2 == other.id2;
}




}
