package kz.narxoz.demo.repository;

import kz.narxoz.demo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
