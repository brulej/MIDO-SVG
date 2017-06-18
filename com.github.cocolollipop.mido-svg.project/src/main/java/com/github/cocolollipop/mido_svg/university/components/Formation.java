package com.github.cocolollipop.mido_svg.university.components;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * This class correspond to a formation of any kind It is an abstract class,
 * which helps a future group to create other kind of formations, but with same
 * "default data" : a title, a grade (1-2-3 for example)
 */
public abstract class Formation {

	// cocolollipop: I dont know if we need all of these...I let you comment
	// these ones
	private char title;
	private String fullName;
	private String intitule;
	// this name corresponds to the name with a link ref to the program
	private String fullNameWithLink;
	// grade is corresponding to the year
	private int grade;
	// Application type (for example: selection based on student records )
	private String admisssion;
	// List of formations you could apply for after the current year
	private List<Formation> availableFormations;
	// list of subjects that contain each formation
	private List<Subject> subjects;
	// the main responsible of the formation
	private Teacher teacher;
	private Point point;
	protected Category category;
	protected boolean shown;

	protected enum Category {
		LICENCE, MASTER
	}

	public Formation(String name, int grade, int x, int y) {
		this.title = ' ';
		this.intitule = " ";
		this.fullName = name;
		this.grade = grade;
		this.point = new Point();
		this.point.setLocation(x, y);
		this.subjects = new ArrayList<>();
		this.availableFormations = new ArrayList<>();
		this.admisssion = "";
		this.teacher = new Teacher();
		this.shown = false;

	}

	public String getFullNameWithLink() {
		if (this.fullNameWithLink == null) {
			return this.fullName;
		}
		return this.fullNameWithLink;
	}

	/**
	 * This is to create a link for a formation. It has to be used with the
	 * method drawString() from Graphics2D
	 * 
	 * @param link
	 */
	public void setFullNameWithLink(String link) {

		this.fullNameWithLink = "<a xlink:href=\"" + link + "\" target=\"_blank\">" + this.getFullName() + "</a>";

	}

	public char getTitle() {
		return title;
	}

	public void setTitle(char nomFormation) {
		this.title = nomFormation;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public int getGrade() {
		return this.grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}

	public String getAdmisssion() {
		return admisssion;
	}

	public void setAdmisssion(String admisssion) {
		this.admisssion = admisssion;
	}

	public List<Formation> getAvailableFormations() {
		return availableFormations;
	}

	public void setAvailableFormations(List<Formation> availableFormations) {
		this.availableFormations = availableFormations;
	}

	public void addAvailableFormation(Formation formation) {
		this.availableFormations.add(formation);
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	/*
	 * public void addSubjectsOfFormation(Subject s) { this.subjects.add(s); }
	 */

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public boolean isShown() {
		return shown;
	}

	public void setShown(boolean shown) {
		this.shown = shown;
	}

	/**
	 * hasGotATeachert see if a "formation" has got a teacher (the
	 * firstname/lastname aren't null) or not
	 * 
	 * @param f
	 * @return true or false
	 */

	public boolean hasGotATeacher(Formation f) {
		if (f.getTeacher().getFirstName() == null && f.getTeacher().getLastName() == null) {
			return false;
		}
		return true;

	}

	public Point getPoint() {
		if (this.point == null) {
			this.point.setLocation(0, 0);
		}
		return point;
	}

	public void setPosX(int i) {
		this.point.setLocation(i, this.getPoint().y);

	}

	public void setPosY(int i) {
		this.point.setLocation(this.getPoint().x, i);

	}

	public Enum<?> getCategory() {
		// TODO Auto-generated method stub
		return this.category;
	}

	public void addSubjectToList(Subject s) {
		this.subjects.add(s);
	}

	/**
	 * This function fills the subjects list of a formation with subjects
	 *
	 * 
	 * @param AllSubjects
	 */

	public void fillsubjectList(List<Subject> AllSubjects) {
		for (Subject s : AllSubjects) {
			if (s.getLevel().getFullName() == this.getFullName()) {
				this.addSubjectToList(s);
			}
		}
	}

}
