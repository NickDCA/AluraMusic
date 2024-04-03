package br.com.nicolas.aluramusic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.nicolas.aluramusic.models.Artist;
import br.com.nicolas.aluramusic.models.Song;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByNameContainingIgnoreCase(String name);

    @Query("SELECT s FROM Artist a JOIN a.songs s")
    List<Song> showAllSongs();

    @Query("SELECT s FROM Artist a JOIN a.songs s WHERE a = :artist")
    List<Song> songsOfArtist(Artist artist);
}
