package com.nathanpulliam.lookify.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nathanpulliam.lookify.models.Song;
import com.nathanpulliam.lookify.services.MainService;

@Controller
public class SongController {

	@Autowired
	private MainService mainServ;
	
	@RequestMapping("")
	public String index() {
		return "index.jsp";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(Model model) {
		List<Song> songsFromDb = mainServ.allSongs();
		model.addAttribute("allSongs", songsFromDb);
		return "dashboard.jsp";
	}
	
	@PostMapping("/search")
	public String search(@RequestParam("searchArtist") String searchArtist) {
		
		return "redirect:/search/" + searchArtist;
	}
	
	@RequestMapping("/search/{searchArtist}")
	public String searchResult(@PathVariable("searchArtist") String searchArtist, Model model) {
		List<Song> songsBySearched = mainServ.songsByArtist(searchArtist);
		model.addAttribute("currentSearch", searchArtist);
		model.addAttribute("songsByArtist", songsBySearched);
		return "searched.jsp";
	}
	
	@RequestMapping("songs/new")
	public String addSong(@ModelAttribute("songObj") Song emptySong) {
		return "new_song.jsp";
	}
	@PostMapping("songs/create")
	public String createSong(@Valid @ModelAttribute("songObj") Song filledSong, BindingResult results) {
		if(results.hasErrors()) {
			return "new_song.jsp";
		} else {
			mainServ.saveSong(filledSong);
			return "redirect:/dashboard";
		}
	}
	@RequestMapping("songs/topTen")
	public String topTen(Model model) {
		List<Song> songsByRating = mainServ.topRated();
		model.addAttribute("songsByRating", songsByRating);
		return "top_ten.jsp";
	}
	@RequestMapping("songs/{id}")
	public String showSong(@PathVariable("id") Long id, Model model) {
		Song song = mainServ.findSong(id);
		model.addAttribute("song", song);
		return "show_song.jsp";
	}
	
	@RequestMapping("songs/{id}/delete") 
		public String deleteSong(@PathVariable("id") Long id) {
			mainServ.deleteSong(id);	
			return "redirect:/dashboard";
	}
}

