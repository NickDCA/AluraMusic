package br.com.nicolas.aluramusic.main;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import br.com.nicolas.aluramusic.models.Artist;
import br.com.nicolas.aluramusic.models.ArtistType;
import br.com.nicolas.aluramusic.models.Song;
import br.com.nicolas.aluramusic.repository.ArtistRepository;

public class Main {
    private Scanner in = new Scanner(System.in);
    private ArtistRepository repository;

    public Main(ArtistRepository repository) {
        this.repository = repository;
    }

    public void showMenu() {
        int option = -1;
        while (option != 9) {
            var menu = """
                        *** Alura Music ***


                        1- Cadastrar artistas

                        2- Cadastrar músicas

                        3- Listar músicas

                        4- Buscar músicas por artistas

                        5- Pesquisar dados sobre um artista

                        9- Sair

                    """;
            System.out.println(menu);
            option = in.nextInt();
            in.nextLine();

            switch (option) {
                case 1:
                    registerArtist();
                    break;
                case 2:
                    registerSong();
                    break;
                case 3:
                    listSongs();
                    break;
                case 4:
                    searchMusicByArtist();
                    break;
                case 5:
                    searchArtistData();
                    break;
                default:
                    break;
            }
        }
    }

    private Optional<Artist> searchArtistByName() {
        System.out.println("Artist name?");
        String searchInput = in.nextLine();
        Optional<Artist> searchedArtist = repository.findByNameContainingIgnoreCase(searchInput);
        return searchedArtist;
    }

    private void registerArtist() {
        System.out.println("Artist name?");
        String artistName = in.nextLine();
        System.out.println("Solo, duo or a group?");
        String artistTypeString = in.nextLine();
        ArtistType artistType = ArtistType.fromString(artistTypeString);

        Artist artist = new Artist(artistName, artistType);
        repository.save(artist);

    }

    private void registerSong() {

        Optional<Artist> searchedArtist = searchArtistByName();
        if (searchedArtist.isPresent()) {
            System.out.println("Song name?");
            String songName = in.nextLine();
            System.out.println("Album name?");
            String albumName = in.nextLine();

            Artist artist = searchedArtist.get();

            Song song = new Song(songName, albumName, artist);
            artist.getSongs().add(song);
            repository.save(artist);

        } else {
            System.out.println("Searched artist not found");
        }

    }

    private void listSongs() {
        System.out.println("Listing all registered songs...");
        List<Song> songs = repository.showAllSongs();
        songs.forEach(s -> System.out.printf("Name: %s | Album: %s | Artist: %s\n", s.getTitle(), s.getAlbum(),
                s.getArtist().getName()));
    }

    private void searchMusicByArtist() {
        Optional<Artist> searchedArtist = searchArtistByName();

        if (searchedArtist.isPresent()) {
            Artist artist = searchedArtist.get();
            // artist.getSongs().forEach(System.out::println);
            List<Song> artistSongs = repository.songsOfArtist(artist);
            artistSongs
                    .forEach(s -> System.out.printf("Name: %s | Album: %s | Artist: %s\n", s.getTitle(), s.getAlbum(),
                            s.getArtist().getName()));
        }
    }

    private void searchArtistData() {
        System.out.println("estou na função :D");
    }

}
