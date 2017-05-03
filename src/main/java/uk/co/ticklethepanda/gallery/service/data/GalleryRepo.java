package uk.co.ticklethepanda.gallery.service.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepo extends JpaRepository<Gallery, Long> {
}