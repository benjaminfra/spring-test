package com.sw.project.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Problem")
public class Problem implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idx")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@Column(name = "title")
	private String title;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "code", referencedColumnName = "code", nullable = false)
	private Project project; //Problem <-> Project  Many to one 
	
	@OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private final Set<subProblem> subProblems = new HashSet<>();
	
	
	public Problem() { }//JPA constructor
	
	public Problem(String title, final Project project) {
		
		this.project = project;
		this.title = title;
	}
	
	public Long getIdx() {
		return idx;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getProblemCode() {
		return this.project.getCode();
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public void addSubProblem(subProblem subproblem) {
		this.subProblems.add(subproblem);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "idx : "+ idx + " title : "+ title + "code : "+ project.getCode();
	}
}
