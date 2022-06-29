package it.polito.tdp.borders.model;

import java.util.Objects;

public class Country {
private int id;
private String stateAbb;
private String stateName;
public Country(int id, String stateAbb, String stateName) {
	super();
	this.id = id;
	this.stateAbb = stateAbb;
	this.stateName = stateName;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getStateAbb() {
	return stateAbb;
}
public void setStateAbb(String stateAbb) {
	this.stateAbb = stateAbb;
}
public String getStateName() {
	return stateName;
}
public void setStateName(String stateName) {
	this.stateName = stateName;
}
@Override
public int hashCode() {
	return Objects.hash(id);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Country other = (Country) obj;
	return id == other.id;
}
@Override
public String toString() {
	
	return this.stateName;
}



}
