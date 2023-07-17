package com.project.cinemax.service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.cinemax.model.Category;
import com.project.cinemax.model.Cinema;
import com.project.cinemax.model.City;
import com.project.cinemax.model.Hall;
import com.project.cinemax.model.Movie;
import com.project.cinemax.model.Projection;
import com.project.cinemax.model.Role;
import com.project.cinemax.model.Seat;
import com.project.cinemax.model.User;
import com.project.cinemax.repository.CategoryRepository;
import com.project.cinemax.repository.CinemaRepository;
import com.project.cinemax.repository.CityRepository;
import com.project.cinemax.repository.HallRepository;
import com.project.cinemax.repository.MovieRepository;
import com.project.cinemax.repository.ProjectionRepository;
import com.project.cinemax.repository.RoleRepository;
import com.project.cinemax.repository.SeatRepository;
import com.project.cinemax.repository.UserRepository;

@Service
@Transactional
public class InitService {
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private HallRepository hallRepository;
	
	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ProjectionRepository projectionRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CinemaRepository cinemaRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	

	public void initCategories() {
		String[] categories = { "Komedija", "Drama", "Avantura", "Akcija", "Dokumentarac", "Istorija", "Horor",
				"Misterija", "Naučna fantastika", "Ratni", "Animirani film", "Krimi", "Western", "Romansa" };
		for (String c : categories) {
			categoryRepository.save(new Category(c));
		}
	}
	
	public void initMovies() throws ParseException {
		List<Movie> movies = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		movies.add(new Movie(
				"Super špijun Orson mora da pronađe i zaustavi prodaju nove ubitačne tehnologije koju prodaje milijarderski krijumčar oružja Greg Simonds. Nevoljno, udružen sa ekipom najboljih operativaca na svetu, Orson i njegova ekipa regrutuju najveću holivudsku filmsku zvezdu Denija Frančeska, da im pomogne u njihovoj misiji spašavanja sveta.",
				"Guy Ritchie", 114,
				"https://m.media-amazon.com/images/M/MV5BZGFmNmRlZmQtMDAyYy00NTJjLTg2ODQtZDI0OWE3M2I2NDcyXkEyXkFqcGdeQXVyNjY1MTg4Mzc@._V1_.jpg",
				simpleDateFormat.parse("2023-01-05"), "OPERACIJA FORTUNA - PREVARA VEKA",
				categoryRepository.findById((long) 4).get(), 0.0));
		movies.add(new Movie(
				"Zajedno, sa roditeljima Hope - Hank Pym (Michael Douglas) i Janet Van Dyne (Michelle Pfeiffer) i sa ćerkom Scott-a Cassie Lang (Kathryn Newton), porodica se iznenada nalazi u situaciji gde istražuju Kvantnu oblast, i u interakciji sa čudnim novim stvorenjima upuštaju se u avanturu koja će ih gurnuti izvan granica onoga što su zamišljali da je moguće.",
				"Peyton Reed", 125,
				"https://m.media-amazon.com/images/M/MV5BYjcyYTk0N2YtMzc4ZC00Y2E0LWFkNDgtNjE1MzZmMGE1YjY1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UX1000_.jpg",
				simpleDateFormat.parse("2023-02-16"), "ANTMEN I OSA - KVANTUMANIJA",
				categoryRepository.findById((long) 3).get(), 0.0));
		movies.add(new Movie(
				"Uz pomoć Finaltesisa, feničanskog trgovca i njenog vernog telohranitelja Mai Vei, jedina ćerka carice, princeza Sas-Ji beži u Galiju da zatraži pomoć od dva hrabra ratnika Asteriksa i Obeliksa, koji su obdareni nadljudskom snagom zahvaljujući svom magičnom napitku. Naša dva nerazdvojna heroja rado prihvataju da pomognu princezi da spase svoju majku i oslobodi svoju zemlju. I tako počinje veliko putovanje i avantura na putu za Kinu.",
				"Guillaume Canet", 111,
				"https://m.media-amazon.com/images/M/MV5BOTZjMDg1YjktMWFiMS00Y2U3LTlhM2QtNWVkOWE0ODYwNTU3XkEyXkFqcGdeQXVyMTA0MDg1ODQ4._V1_.jpg",
				simpleDateFormat.parse("2023-02-02"), "ASTERIKS I OBELIKS - SREDNJE KRALJEVSTVO",
				categoryRepository.findById((long) 3).get(), 0.0));
		movies.add(new Movie(
				"Džejk Sali živi sa svojom porodicom na planeti Pandori. Kada se vrati poznati neprijatelj koji pokušava da ih uništi, Džejk i Nejtiri se udružuju sa vojskom Navi naroda kako bi zaštitili svoju planetu.",
				"James Cameron", 190,
				"https://m.media-amazon.com/images/M/MV5BYjhiNjBlODctY2ZiOC00YjVlLWFlNzAtNTVhNzM1YjI1NzMxXkEyXkFqcGdeQXVyMjQxNTE1MDA@._V1_FMjpg_UX1000_.jpg",
				simpleDateFormat.parse("2022-12-22"), "AVATAR - PUT VODE", categoryRepository.findById((long) 9).get(),
				0.0));
		movies.add(new Movie(
				"Nakon što ga je propali poslovni dogovor ostavio bez novca, Majk je prisiljen da radio kao barmen na Floridi. Nadajući se poslednjem dobrom provodu, Majk beži u London sa poznatom bogatašicom koja mu daje ponudu koju ne može da odbije...",
				"Steven Soderbergh", 107,
				"https://m.media-amazon.com/images/M/MV5BMTg4ZWZhODQtNWVhNC00NjA1LWJhZjgtZTFkY2JmNWJlYzU0XkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_FMjpg_UX1000_.jpg",
				simpleDateFormat.parse("2023-02-09"), "ČAROBNI MAJK - POSLEDNJI PLES",
				categoryRepository.findById((long) 2).get(), 0.0));
		movies.add(new Movie(
				"Film Čovek po imenu Oto govori o Otou Andersonu (Tom Henks), mrzovoljnom udovcu kome radost donosi jedino kritikovanje i osuđivanje svojih ogorčenih komšija. Kada se živahna mlada porodica doseli u komšiluk, upoznaje svog parnjaka u oštroumnoj i veoma trudnoj Marisol, što vodi do neočekivanog prijateljstva koje će okrenuti njegov svet naglavačke.",
				"Marc Forster", 126,
				"https://m.media-amazon.com/images/M/MV5BMDQyYzFjYmEtMDRmOS00MmI3LTg5M2QtMWQ1YzE3NzBlNjhlXkEyXkFqcGdeQXVyMTA3MDk2NDg2._V1_.jpg",
				simpleDateFormat.parse("2023-02-02"), "ČOVEK PO IMENU OTO", categoryRepository.findById((long) 1).get(),
				0.0));
		movies.add(new Movie(
				"Dok pravi poslednji, hrabar pokušaj da se pomiri sa svojom razorenom porodicom, Čarli mora da se suoči, punim srcem i žestokom duhovitošc´u, sa davno zakopanim traumama neizrečene ljubavi koja ga proganja decenijama.",
				"Darren Aronofsky", 117,
				"https://m.media-amazon.com/images/M/MV5BZDQ4Njg4YTctNGZkYi00NWU1LWI4OTYtNmNjOWMyMjI1NWYzXkEyXkFqcGdeQXVyMTA3MDk2NDg2._V1_.jpg",
				simpleDateFormat.parse("2023-02-02"), "KIT", categoryRepository.findById((long) 2).get(), 0.0));
		movies.add(new Movie(
				"Posle više od decenije, Dream Works Animation predstavlja novu avanturu iz Šrekovog univerzuma. Odvažni odmetnik, Mačak u čizmama otkriće da su njegova strast prema opasnosti i zanemarivanje bezbednosti došli po svoje.",
				"Joel Crawford, Januel Mercado", 101,
				"https://m.media-amazon.com/images/M/MV5BNjMyMDBjMGUtNDUzZi00N2MwLTg1MjItZTk2MDE1OTZmNTYxXkEyXkFqcGdeQXVyMTQ5NjA0NDM0._V1_FMjpg_UX1000_.jpg",
				simpleDateFormat.parse("2022-12-08"), "MAČAK U ČIZMAMA: POSLEDNJA ŽELJA",
				categoryRepository.findById((long) 11).get(), 0.0));
		movies.add(new Movie(
				"Kada Normova deca saznaju za njegovo tajno putovanje u živopisni grad Harbin, odlučuju da to pretvore u porodičnu avanturu, nesvesni da c´e Norm zapravo tražiti svoju izgubljenu krunu. Dok su u Harbinu, upoznaju tigra po imenu Fu, arhitektu grada i ponosnog oca blizanaca, Pita i Repeta.",
				"Anthony Bell", 89,
				"https://m.media-amazon.com/images/M/MV5BZWQyYmQ1NDktYTg2Ni00OWNmLTk3OTEtNzE1MjcwZjgxNWMzXkEyXkFqcGdeQXVyMjM5NjM5NzU@._V1_FMjpg_UX1000_.jpg",
				simpleDateFormat.parse("2023-02-09"), "MEDA SA SEVERA 4", categoryRepository.findById((long) 11).get(),
				0.0));
		movies.add(new Movie(
				"Kada Normova deca saznaju za njegovo tajno putovanje u živopisni grad Harbin, odlučuju da to pretvore u porodičnu avanturu, nesvesni da c´e Norm zapravo tražiti svoju izgubljenu krunu. Dok su u Harbinu, upoznaju tigra po imenu Fu, arhitektu grada i ponosnog oca blizanaca, Pita i Repeta.",
				"Caroline Origer", 97,
				"https://m.media-amazon.com/images/M/MV5BNjNkMTZmOGYtZjgzNC00NDQ3LTllOTktM2IzMTdhOWJlOGI0XkEyXkFqcGdeQXVyMTI3NTEzMjky._V1_.jpg",
				simpleDateFormat.parse("2023-02-16"), "MOJA VILA ŠEPRTLJA",
				categoryRepository.findById((long) 11).get(), 0.0));
		movies.add(new Movie(
				"Asusena ima 24h da spreči da njena porodica i ona budu izbačene iz svog doma od strane banke koja teži da vrati stan u svoj posed. Rafael je veoma posvećeni advokat i aktivista koji nastoji da reši ovakve tragedije modernog vremena.",
				"Juan Diego Botto", 105,
				"https://m.media-amazon.com/images/M/MV5BMGM1MWI0NzctYWE5NS00NDQzLTk2MGUtNWY0ZGUzYWFmNTlkXkEyXkFqcGdeQXVyMTA0MjU0Ng@@._V1_.jpg",
				simpleDateFormat.parse("2023-02-16"), "NA MARGINI", categoryRepository.findById((long) 2).get(), 0.0));
		movies.add(new Movie(
				"Tokom odmora u udaljenoj kolibi, devojčicu i njene roditelje četiri naoružana stranca uzimaju za taoce, zahtevajući da porodica napravi nezamisliv izbor kako bi sprečili apokalipsu.",
				"M. Night Shyamalan", 100,
				"https://images.fandango.com/ImageRenderer/0/0/redesign/static/img/default_poster.png/0/images/masterrepository/Fandango/229426/KCN_Tsr1Sht77Rev_RGB_1.jpg",
				simpleDateFormat.parse("2023-02-02"), "NEKO KUCA NA VRATA KOLIBE",
				categoryRepository.findById((long) 7).get(), 0.0));
		movies.add(new Movie(
				"U želji da spreči dalje napade na selo, gde je zamalo ostao bez sina Petra, Ilija formira diverzantsku jedinicu sa ciljem da uništi neprijateljsko gnezdo odakle se selo napada.",
				"Miloš Radunović", 123,
				"https://m.media-amazon.com/images/M/MV5BZWJmNGIxZTAtYTAyOC00OGIxLWExOTQtNjYwMmI0N2VkMTY4XkEyXkFqcGdeQXVyMjM5MTg4MjQ@._V1_.jpg",
				simpleDateFormat.parse("2023-01-19"), "OLUJA", categoryRepository.findById((long) 10).get(), 0.0));
		movies.add(new Movie(
				"Pilot se nađe uhvaćen u ratnoj zoni nakon što je primoran da spusti svoj komercijalni avion tokom strašne oluje.",
				"Jean-François Richet", 108,
				"https://m.media-amazon.com/images/M/MV5BZDc4MzVkNzYtZTRiZC00ODYwLWJjZmMtMDIyNjQ1M2M1OGM2XkEyXkFqcGdeQXVyMDA4NzMyOA@@._V1_FMjpg_UX1000_.jpg",
				simpleDateFormat.parse("2023-01-26"), "OPASAN LET", categoryRepository.findById((long) 4).get(), 0.0));
		movies.add(new Movie(
				"Nakon što joj je brat, koji je bio sveštenik, nastradao pod sumnjivim okolnostima, Grejs odlazi u Škotsku, u manastir Spasitelja kako bi saznala šta se zaista dogodilo. Ono što će otkriti jeste ubistvo, svetogrđe, ali i uznemirujuća istina o sopstvenoj prošlosti.",
				"Christopher Smith", 91,
				"https://m.media-amazon.com/images/M/MV5BMTBhMTYxZDYtNjc3MS00MTM2LWFkMzAtOTAyMDJjNjdkNGE1XkEyXkFqcGdeQXVyMTA3MDk2NDg2._V1_.jpg",
				simpleDateFormat.parse("2023-02-09"), "POSVEĆENJE ZLA", categoryRepository.findById((long) 7).get(),
				0.0));
		movies.add(new Movie(
				"Za rediteljku dokumentaraca i zavisnicu od aplikacija za sastanke Zoe (Lili Džejms), prevlačenje udesno je samo isporučilo beskrajni striming Mr Vrongsa, na užas njene ekscentrične majke Kejt (Ema Tompson). Za Zoinog prijatelja iz detinjstva i komšiju Kaza (Šazad Latif), odgovor je da sledi primer svojih roditelja i odluči se za ugovoreni brak sa bistrom i lepom nevestom iz Pakistana.",
				"Shekhar Kapur", 108,
				"https://m.media-amazon.com/images/M/MV5BZmFiZmRjZmMtZjZhYi00ODM2LWIyMTItMjZmNTljYjU4M2ZkXkEyXkFqcGdeQXVyNjg5MDIxMTk@._V1_FMjpg_UX1000_.jpg",
				simpleDateFormat.parse("2023-02-16"), "ŠTA LJUBAV IMA S TIM?",
				categoryRepository.findById((long) 14).get(), 0.0));
		movies.add(new Movie(
				"Jedan od tri broda britanske kompanije \"Vajt star lajn\", najveći putnički brod ikada izgrađen do te 1912. kreće na putovanje od Sautemptona do Njujorka sa više od 1.500 putnika među kojima su i Rouz, dama iz visokog društva, i Džejk, siromašan momak koji se u nju zaljubljuje.",
				"James Cameron", 195,
				"https://static.euronews.com/articles/stories/07/31/01/20/606x758_cmsv2_dfdbec3d-af7a-5e75-9ae7-2ec0188cbf9a-7310120.jpg",
				simpleDateFormat.parse("2023-02-09"), "TITANIK: 25 GODINA",
				categoryRepository.findById((long) 14).get(), 0.0));
		movies.add(new Movie(
				"Sve ih pratimo u jednom danu, u jednom stanu, za vreme slave Usekovanje glave Svetog Jovana Krstitelja, gde roditelji kriju od Jovana i gostiju da se razvode.",
				"Siniša Cvetić", 123,
				"https://m.media-amazon.com/images/M/MV5BNzliZjBlMGItN2ExOC00YmI3LTk0ZjAtOGFlOGVkODY4MGViXkEyXkFqcGdeQXVyNjM0MjA4NjI@._V1_FMjpg_UX1000_.jpg",
				simpleDateFormat.parse("2023-02-09"), "USEKOVANJE", categoryRepository.findById((long) 2).get(), 0.0));
		movies.add(new Movie(
				"Priča o ogromnoj ambiciji i nečuvenim ekscesima, prati uspone i padove više likova tokom ere neobuzdane dekadencije i izopačenosti koja je vladala u počecima Holivuda.",
				"Damien Chazelle", 189,
				"https://m.media-amazon.com/images/M/MV5BNzZiOWQzYTYtMDNhYS00Y2NlLTkzODUtMWExM2U1NmZkMTA5XkEyXkFqcGdeQXVyODU0NTY4NTc@._V1_.jpg",
				simpleDateFormat.parse("2023-01-12"), "VAVILON", categoryRepository.findById((long) 6).get(), 0.0));
		movieRepository.saveAll(movies);
	}

	public void initCities() {
		String[] cities = { "Beograd", "Novi Sad", "Niš" };
		for (String c : cities) {
			cityRepository.save(new City(c));
		}
	}

	public void initCinemas() {
		String[] cinemas = { "CINEMAX - DELTA CITY", "CINEMAX - BEO SHOPING CENTAR", "CINEMAX - BIG",
				"CINEMAX - PROMENADA", "CINEMAX - BIG", "CINEMAX - SPENS", "CINEMAX - BIG", "CINEMAX - DELTA PLANET",
				"CINEMAX - PANDA" };
		Long cityId;
		for (int i = 0; i < cinemas.length; i++) {
			if (i < 3) {
				cityId = (long) 1;
			} else if (i >= 3 && i < 6) {
				cityId = (long) 2;
			} else {
				cityId = (long) 3;
			}
			cinemaRepository.save(new Cinema(cinemas[i], cityRepository.findById(cityId).get()));
		}
	}

	public void initHalls() {
		List<Cinema> cinemas = cinemaRepository.findAll();
		for (Cinema c : cinemas) {
			hallRepository.save(new Hall("MALA", 80, c));
			hallRepository.save(new Hall("VELIKA", 100, c));
		}
	}

	public void initSeats() {
		List<Hall> halls = hallRepository.findAll();
		for (Hall hall : halls) {
			for (int i = 0; i < hall.getSeatNumber(); i++) {
				seatRepository.save(new Seat(i + 1, hall));
			}
		}
	}

	public void initProjections() {
		List<Movie> movies = movieRepository.findAll();
		Long[] moviesId = new Long[movies.size()];
		int index = 0;
		for (Movie m : movies) {
			moviesId[index] = m.getId();
			index++;
		}
		for (int i = 0; i < 7; i++) {
			Date date = new Date();
			if (i > 0) {
				date = new Date(date.getTime() + i * (1000 * 60 * 60 * 24));
			}
			List<Hall> halls = hallRepository.findAll();
			for (Hall hall : halls) {
				for (int j = 0; j < 4; j++) {
					int hour = j * 3 + 14;
					Projection proj = new Projection(date, (randomInt(3, 6) * 100), new Time(hour, 0, 0), hall,
							movieRepository.findById(getRandomElement(moviesId)).get());
					projectionRepository.save(proj);
				}
			}
		}
	}

	private static Long getRandomElement(Long[] array) {
		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}

	private int randomInt(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	public void initRoles() {
		roleRepository.save(new Role("Admin", "Administrator role."));
		roleRepository.save(new Role("User", "Logged User role."));
	}
	
	public void initUsers() {
		User user = new User("admin","Admin", "Adminić", "admin");
		Role role = roleRepository.findById("Admin").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        userRepository.save(user);
        
        User user1 = new User("pera","Petar", "Perić", "pera");
		Role role1 = roleRepository.findById("User").get();
        Set<Role> userRoles1 = new HashSet<>();
        userRoles1.add(role1);
        user1.setRoles(userRoles1);
        user1.setUserPassword(getEncodedPassword(user1.getUserPassword()));
        userRepository.save(user1);
	}
	
	public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
	
	public void resetAutoIncrementValue(String tableName) {
        String sqlQuery = "ALTER TABLE " + tableName + " AUTO_INCREMENT = 1";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.executeUpdate();
    }

}
