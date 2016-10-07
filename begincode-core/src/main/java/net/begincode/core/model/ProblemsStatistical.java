package net.begincode.core.model;

import java.util.Set;

public class ProblemsStatistical {
	private int views;
	private Set<Integer> collections;
	private Set<Integer> votes;
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Set<Integer> getCollections() {
		return collections;
	}
	public void setCollections(Set<Integer> collections) {
		this.collections = collections;
	}
	public Set<Integer> getVotes() {
		return votes;
	}
	public void setVotes(Set<Integer> votes) {
		this.votes = votes;
	}
	
}
