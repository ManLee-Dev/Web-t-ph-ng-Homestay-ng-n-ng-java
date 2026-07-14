package com.homestay.booking.controller;

import com.homestay.booking.dto.CreateHomestayRequest;
import com.homestay.booking.model.Homestay;
import com.homestay.booking.model.User;
import com.homestay.booking.repository.HomestayRepository;
import com.homestay.booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/homestays")
@RequiredArgsConstructor
public class HomestayController {

    private final HomestayRepository homestayRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createHomestay(@RequestBody CreateHomestayRequest req) {
        Optional<User> ownerOpt = userRepository.findById(req.getOwnerId());
        if (ownerOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Owner not found");
        }

        Homestay h = new Homestay();
        h.setName(req.getName());
        h.setDescription(req.getDescription());
        h.setAddress(req.getAddress());
        h.setPricePerNight(req.getPricePerNight());
        h.setImageUrl(req.getImageUrl());
        h.setStatus(req.getStatus());
        h.setOwner(ownerOpt.get());

        Homestay saved = homestayRepository.save(h);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Homestay> listHomestays() {
        return homestayRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHomestay(@PathVariable Long id) {
        return homestayRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHomestay(@PathVariable Long id, @RequestBody CreateHomestayRequest req) {
        Optional<Homestay> opt = homestayRepository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Homestay h = opt.get();
        h.setName(req.getName());
        h.setDescription(req.getDescription());
        h.setAddress(req.getAddress());
        h.setPricePerNight(req.getPricePerNight());
        h.setImageUrl(req.getImageUrl());
        h.setStatus(req.getStatus());

        if (req.getOwnerId() != null) {
            userRepository.findById(req.getOwnerId()).ifPresent(h::setOwner);
        }

        Homestay saved = homestayRepository.save(h);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHomestay(@PathVariable Long id) {
        if (!homestayRepository.existsById(id)) return ResponseEntity.notFound().build();
        homestayRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
