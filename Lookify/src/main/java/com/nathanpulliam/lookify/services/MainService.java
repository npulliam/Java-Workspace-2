package com.nathanpulliam.lookify.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nathanpulliam.lookify.models.Song;
import com.nathanpulliam.lookify.repositories.SongRepository;

@Service
public class MainService {
	private final SongRepository songRepository;
	
	public MainService(SongRepository songRepository) {
		this.songRepository = songRepository;
		
	}
	public Song saveSong(Song song) {
		return songRepository.save(song);
	}
	
	public Song findSong(Long id) {
		return songRepository.findById(id).orElse(null);
	}
	
	
	public List<Song> allSongs() {
		return songRepository.findAll();
	}
	
	public List<Song> songsByArtist(String artist) {
		return songRepository.findByArtistContaining(artist);
	}
}
