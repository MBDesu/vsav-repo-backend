package org.jordyn.vsav.service.sql;

/**
 * Class containing SQL that {@link org.jordyn.vsav.service.api.VideoService} uses
 * @author jordyn
 *
 */
public class VideoServiceSql {
	// TODO: convert all this to Hibernate queries instead of native?
	public static final String retrieveVideos = "FROM video ORDER BY lastUpdate DESC";
	public static final String retrieveMatches = "FROM match ORDER BY video.metadata.uploadDate DESC";
	public static final String addVideo = "INSERT INTO vs.videos (C_YT_ID) VALUES(?)";
	public static final String addMatchToLastAddedVideo = "INSERT INTO vs.matches (I_VID, C_PLYR_ONE, C_PLYR_TWO, C_CHAR_ONE, C_CHAR_TWO, C_YT_TIMESTAMP) VALUES(last_insert_id(), ?, ?, ?, ?, ?)";
	public static final String getLastInsertedVideo = "SELECT * FROM vs.videos WHERE I_VID = (SELECT I_VID FROM vs.matches WHERE I_MTCH_ID = last_insert_id())";
	public static final String addMatchToVideo = "INSERT INTO vs.matches (I_VID, C_PLYR_ONE, C_PLYR_TWO, C_CHAR_ONE, C_CHAR_TWO, C_YT_TIMESTAMP) VALUES(?, ?, ?, ?, ?, ?)";
	public static final String retrieveVideo = "SELECT * FROM vs.videos WHERE I_VID = ?";
	public static final String retrieveVideoFromMatch = "SELECT * FROM vs.videos WHERE I_VID = (SELECT I_VID FROM vs.matches where I_MTCH_ID = ?)";
	public static final String retrieveVideosByPlayer = "SELECT DISTINCT(video) FROM match WHERE (C_PLYR_ONE_IDX = ?) OR (C_PLYR_TWO_IDX = ?)";
	public static final String addMetadata = "INSERT INTO vs.video_metadata (I_VID, C_TITLE, C_DESC, C_CHNNL, Z_DATE) VALUES(?, ?, ?, ?, ?)";
	// public static final String getLastInsertId = "SELECT last_insert_id()";
	public static final String retrieveMatch = "SELECT * FROM vs.matches WHERE I_MTCH_ID=?";
	public static final String getVideoIdByYoutubeId = "SELECT I_VID FROM vs.videos WHERE C_YT_ID=?";
	public static final String retrieveUniquePlayers = "select C_PLYR_ONE as player from matches as m union select C_PLYR_TWO from matches order by player asc";
}
