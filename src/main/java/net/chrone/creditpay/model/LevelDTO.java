package net.chrone.creditpay.model;

import java.util.List;

public class LevelDTO {

	private List<Level> levels;
	
	private List<AgentLevel> agentLevels;

	public List<Level> getLevels() {
		return levels;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}

	public List<AgentLevel> getAgentLevels() {
		return agentLevels;
	}

	public void setAgentLevels(List<AgentLevel> agentLevels) {
		this.agentLevels = agentLevels;
	}
	
	

}
