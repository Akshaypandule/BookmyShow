package com.BookMyShow.ServiceImpl;

import com.BookMyShow.dto.*;
import com.BookMyShow.entity.*;
import com.BookMyShow.exception.ResourceNotFound;
import com.BookMyShow.repository.ATMRepository;
import com.BookMyShow.repository.BookedSetRepository;
import com.BookMyShow.repository.BookingRepository;
import com.BookMyShow.repository.UserRepository;
import com.BookMyShow.service.BookingService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookedSetRepository bookedSetRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private MoviesServiceImpl moviesService;
    @Autowired
    private ATMRepository atmRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Transactional
    @Override
    public BookingDto createBooking(BookingDto bookingDto)
    {
        UserDto userDto = Optional.ofNullable(userService.getUserById(bookingDto.getUser().getId()))
                .orElseThrow(() -> new ResourceNotFound("User id is not found"));

        MovieDto invalidMoviesId = Optional.ofNullable(moviesService.FindMoviesById(bookingDto.getMovie().getId()))
                .orElseThrow(() -> new ResourceNotFound("Invalid movies id"));

        ATMDto atmDto = bookingDto.getAtm();
        if(atmDto==null)
        {
            throw new ResourceNotFound("Payment information is required");
        }
        Booking booking = modelMapper.map(bookingDto, Booking.class);
        User userEntity = modelMapper.map(userDto, User.class);
        ATM atmEntity = modelMapper.map(atmDto, ATM.class);

        ATM atmSave = atmRepository.save(atmEntity);
        Movies movieEntity = modelMapper.map(invalidMoviesId, Movies.class);
        booking.setUser(userEntity);
        booking.setMovie(movieEntity);
        booking.setAtm(atmSave);

        //  checking seat Availability
        List<BookedSeatsDto> bookedSeatDtoList = bookingDto.getBookedSeats();
        List<BookedSeats> bookedSetsList = bookedSeatDtoList.stream().map(bookedSeatsDto -> {
            BookedSeats bookedSeats = modelMapper.map(bookedSeatsDto, BookedSeats.class);
            bookedSeats.setBooking(booking);
            return bookedSeats;
        }).collect(Collectors.toList());

        // Price Handling For Seats Category



        
        boolean setAvailable = checkSeatsAvailability(
                booking.getTheaterName(),
                booking.getLocation(),
                booking.getTime(),
                bookedSeatDtoList);
        if (!setAvailable)
        {
            throw new ResourceNotFound("Sets are already Booked.");
        }
        int size = bookingDto.getBookedSeats().size();
        if(handleSetPrice(bookingDto)){
            Double price1 = bookingDto.getPrice();
            booking.setPrice(price1);
            double ticketPrice = size * price1;

            String food = bookingDto.getFood();
            String[] split = food.split("=");
            double price = Double.parseDouble(split[1]);
            double TotalPrice =ticketPrice +price;
            booking.setTotalPrice(TotalPrice);
        }
        if(size>=4){
            throw new ResourceNotFound("Only Four set will be booked by one user");
        }
        // Calculate total price of ticket

        //if(handleSetPrice())

//        if(bookingDto.getBookedSeats().size()>4){
//            throw new ResourceNotFound("Only Four set will be booked by one user");
//        }
        List<BookedSeats> bookedSets = bookedSetRepository.saveAll(bookedSetsList);
        booking.setBookedSeats(bookedSets);

        Booking savedBooking = bookingRepository.save(booking);
        return bookingToBookingDto(savedBooking);
    }
    public boolean checkSeatsAvailability(String theaterName, String location, String time,
                                          List<BookedSeatsDto> bookedSets)
    {
        List<Booking> alreadyBooked = bookingRepository.findByTheaterNameAndLocationAndTime(theaterName, location, time);

        if(alreadyBooked==null || alreadyBooked.isEmpty())
        {
            return true;
        }
        for(Booking checkBooking:alreadyBooked){
            List<BookedSeats> bookedSeats = checkBooking.getBookedSeats();
            
            for(BookedSeatsDto bookedSeatsDto:bookedSets)
            {
                for(BookedSeats bookedSeats1:bookedSeats){

                    if(bookedSeats1.getSeatNumber().equals(bookedSeatsDto.getSeatNumber())){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    @Override
    public List<BookingDto> getAllBooking() {

        List<Booking> all = bookingRepository.findAll();

        List<BookingDto> collect = all.stream().map(booking -> bookingToBookingDto(booking)).collect(Collectors.toList());
        return collect;
    }

    public boolean handleSetPrice(BookingDto bookingDto){

        List<BookedSeatsDto> bookedSeats = bookingDto.getBookedSeats();

        for(BookedSeatsDto getSeat:bookedSeats){

            String seatNumber = getSeat.getSeatNumber();

            if(seatNumber.startsWith("P")){
                bookingDto.setPrice(700.00);
                return true;
            } else if (seatNumber.startsWith("G")) {
                bookingDto.setPrice(500.00);

            } else if (seatNumber.startsWith("S")) {
                bookingDto.setPrice(300.00);
                return true;
            }

        }
        return false;
//
    }



    @Override
    public List<BookingDto> getBookingByEmail(String email)
    {
//        UserDto userByEmail = userService.getUserByEmail(email);
//        User user = modelMapper.map(userByEmail, User.class);
//        List<Booking> bookingList = user.getBookingList();
//        return bookingList.stream().map(booking -> modelMapper.map(booking,BookingDto.class))
//                .collect(Collectors.toList());

        User user= userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFound("User name is not found"));

        List<Booking> bookingList = user.getBookingList();
        return bookingList.stream().map(booking -> modelMapper.map(booking,BookingDto.class))
                .collect(Collectors.toList());
    }
    private BookingDto bookingToBookingDto(Booking booking) {
        BookingDto map = modelMapper.map(booking, BookingDto.class);
        return map;
    }
    private Booking bookingDtoToBooking(BookingDto bookingDto) {
        Booking map = modelMapper.map(bookingDto, Booking.class);

        return map;
    }
}
