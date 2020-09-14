package org.jordyn.vsav.db.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Domain object representing video metadata
 * @author jordyn
 *
 */
@Entity(name = "metadata")
@Table(name = "VIDEO_METADATA", schema = "vs")
public class VideoMetadata {

	@Id
	@GeneratedValue
	@Column(name = "I_META_ID")
	private Integer metadataId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "I_VID")
	@JsonBackReference
	private Video video;

	@Column(name = "C_TITLE")
	private String title;

	@Column(name = "C_DESC")
	private String description;

	@Column(name = "C_CHNNL")
	private String channel;

	@Column(name = "Z_DATE")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date uploadDate;

	public Integer getMetadataId() {
		return metadataId;
	}

	public void setMetadataId(final Integer metadataId) {
		this.metadataId = metadataId;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(final Video video) {
		this.video = video;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(final String channel) {
		this.channel = channel;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(final Date uploadDate) {
		this.uploadDate = uploadDate;
	}



}
