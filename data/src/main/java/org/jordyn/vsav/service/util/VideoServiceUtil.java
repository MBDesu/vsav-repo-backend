package org.jordyn.vsav.service.util;

import javax.persistence.Query;

import org.jordyn.vsav.contract.VideoContract;

/**
 * Utility class for {@link org.jordyn.vsav.service.api.VideoService}
 * @author jordyn
 *
 */
public class VideoServiceUtil {

	public static void populateAddMatchToVideoQueryParameters(final Query addMatchToVideoQuery, final VideoContract contract) {
		addMatchToVideoQuery.setParameter(1, contract.getVideoId());
		addMatchToVideoQuery.setParameter(2, contract.getPlayerOneName());
		addMatchToVideoQuery.setParameter(3, contract.getPlayerTwoName());
		addMatchToVideoQuery.setParameter(4, contract.getPlayerOneChar());
		addMatchToVideoQuery.setParameter(5, contract.getPlayerTwoChar());
		addMatchToVideoQuery.setParameter(6, contract.getYoutubeTimestamp());
	}

	public static void populateAddMetadataQueryParameters(final Query addMetadataQuery, final VideoContract contract) {
		addMetadataQuery.setParameter(1, contract.getVideoId());
		addMetadataQuery.setParameter(2, contract.getTitle());
		addMetadataQuery.setParameter(3, contract.getDescription());
		addMetadataQuery.setParameter(4, contract.getChannel());
		addMetadataQuery.setParameter(5, contract.getUploadDate());
	}

}
