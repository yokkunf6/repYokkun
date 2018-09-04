package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.FileUtil;

public class Team {

	/*
	 * key=旧名, value=新名
	 */
	private static Map<String, String> newestNameList = new HashMap<String, String>();

	private String teamName;
	private String pref;


	public Team(String teamName, String pref) {
		this.teamName = teamName;
		this.pref = pref;
	}

	public String getTeamName() {
		return teamName;
	}
	public String getPref() {
		return pref;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Team)) return false;
		Team team = (Team)obj;
		if (this.getTeamName().equals(team.getTeamName()) &&
			this.getPref().equals(team.getPref())) {
			return true;
		} else if (this.getPref().equals(team.getPref())) {
			if (this.getNewestTeamName().equals(team.getNewestTeamName())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String getNewestTeamName() {
		if (newestNameList.size() == 0) {
			loadNewestNameList();
		}

		if (newestNameList.containsKey(teamName)) {
			return newestNameList.get(teamName);
		} else {
			return teamName;
		}
	}

	private void loadNewestNameList() {
		try {
			List<String> lines = FileUtil.readFile(Main.BASEBALL_TEAMNAME_LIST);
			for (String line : lines) {
				String oldName = line.split("\t")[0];
				String newName = line.split("\t")[1];
				newestNameList.put(oldName, newName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
