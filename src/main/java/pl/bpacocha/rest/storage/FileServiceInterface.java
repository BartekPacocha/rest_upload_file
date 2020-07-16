package pl.bpacocha.rest.storage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileServiceInterface extends JpaRepository<DataModel, String> {
}
