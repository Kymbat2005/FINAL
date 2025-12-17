-- V2__insert_data.sql
-- Вставка тестовых данных для системы учета студентов и предметов

-- 1. Вставка предметов
INSERT INTO t_subject (subject_id, t_name, t_teacher) VALUES
                                                          (1, 'Математика', 'Иванов Иван Иванович'),
                                                          (2, 'Физика', 'Петров Петр Петрович'),
                                                          (3, 'Информатика', 'Сидорова Анна Владимировна'),
                                                          (4, 'История', 'Кузнецова Елена Сергеевна'),
                                                          (5, 'Английский язык', 'Смирнова Ольга Владимировна'),
                                                          (6, 'Химия', 'Васильев Алексей Николаевич'),
                                                          (7, 'Биология', 'Морозова Татьяна Викторовна'),
                                                          (8, 'Литература', 'Николаев Дмитрий Александрович'),
                                                          (9, 'Физкультура', 'Павлов Сергей Михайлович'),
                                                          (10, 'Экономика', 'Федорова Марина Игоревна')
    ON CONFLICT (subject_id) DO UPDATE SET
    t_name = EXCLUDED.t_name,
                                    t_teacher = EXCLUDED.t_teacher;

-- 2. Вставка студентов
INSERT INTO t_student (student_id, t_name, t_lastname, t_email) VALUES
                                                                    (1, 'Алексей', 'Иванов', 'alexey.ivanov@university.kz'),
                                                                    (2, 'Мария', 'Петрова', 'maria.petrova@university.kz'),
                                                                    (3, 'Дмитрий', 'Сидоров', 'dmitry.sidorov@university.kz'),
                                                                    (4, 'Екатерина', 'Кузнецова', 'ekaterina.kuznetsova@university.kz'),
                                                                    (5, 'Анна', 'Смирнова', 'anna.smirnova@university.kz'),
                                                                    (6, 'Арман', 'Нургалиев', 'arman.nurgaliev@university.kz'),
                                                                    (7, 'Айгерим', 'Сапарова', 'aigerim.saparova@university.kz'),
                                                                    (8, 'Данияр', 'Жумабаев', 'daniyar.zhumabaev@university.kz'),
                                                                    (9, 'Алина', 'Каримова', 'alina.karimova@university.kz'),
                                                                    (10, 'Руслан', 'Абдуллин', 'ruslan.abdullin@university.kz')
    ON CONFLICT (student_id) DO UPDATE SET
    t_name = EXCLUDED.t_name,
                                    t_lastname = EXCLUDED.t_lastname,
                                    t_email = EXCLUDED.t_email;

-- 3. Вставка связей студент-предмет (student_subject)
INSERT INTO student_subject (student_id, subject_id) VALUES
-- Алексей Иванов
(1,1),(1,2),(1,3),(1,5),
-- Мария Петрова
(2,1),(2,4),(2,8),(2,5),
-- Дмитрий Сидоров
(3,3),(3,1),(3,2),(3,6),
-- Екатерина Кузнецова
(4,4),(4,8),(4,10),(4,5),
-- Анна Смирнова
(5,7),(5,6),(5,1),(5,9),
-- Арман Нургалиев
(6,10),(6,1),(6,3),(6,5),
-- Айгерим Сапарова
(7,4),(7,8),(7,5),(7,9),
-- Данияр Жумабаев
(8,2),(8,1),(8,3),(8,6),
-- Алина Каримова
(9,7),(9,6),(9,1),(9,4),
-- Руслан Абдуллин
(10,9),(10,10),(10,4),(10,5)
    ON CONFLICT (student_id, subject_id) DO NOTHING;

-- 4. Вставка финальных работ/экзаменов (t_finals)
INSERT INTO t_finals (final_id, t_name, t_date, student_id, subject_id) VALUES
                                                                            (1, 'Итоговый экзамен по Математике', '2024-01-15', 1, 1),
                                                                            (2, 'Зачет по Физике', '2024-01-16', 1, 2),
                                                                            (3, 'Экзамен по Информатике', '2024-01-17', 1, 3),
                                                                            (4, 'Итоговый экзамен по Математике', '2024-01-15', 2, 1),
                                                                            (5, 'Экзамен по Истории', '2024-01-18', 2, 4),
                                                                            (6, 'Итоговый экзамен по Математике', '2024-01-15', 3, 1),
                                                                            (7, 'Экзамен по Химии', '2024-01-19', 3, 6),
                                                                            (8, 'Зачет по Литературе', '2024-01-20', 4, 8),
                                                                            (9, 'Экзамен по Экономике', '2024-01-21', 4, 10),
                                                                            (10, 'Итоговый экзамен по Биологии', '2024-01-22', 5, 7),
                                                                            (11, 'Экзамен по Английскому языку', '2024-02-10', 1, 5),
                                                                            (12, 'Экзамен по Английскому языку', '2024-02-10', 2, 5),
                                                                            (13, 'Итоговый экзамен по Физике', '2024-02-12', 3, 2),
                                                                            (14, 'Экзамен по Английскому языку', '2024-02-10', 4, 5),
                                                                            (15, 'Зачет по Физкультуре', '2024-02-14', 5, 9),
                                                                            (16, 'Экзамен по Экономике', '2024-02-15', 6, 10),
                                                                            (17, 'Экзамен по Английскому языку', '2024-02-10', 6, 5),
                                                                            (18, 'Зачет по Физкультуре', '2024-02-14', 7, 9),
                                                                            (19, 'Экзамен по Информатике', '2024-02-16', 8, 3),
                                                                            (20, 'Экзамен по Истории', '2024-02-17', 9, 4),
                                                                            (21, 'Экзамен по Английскому языку', '2024-02-10', 10, 5),
                                                                            (22, 'Зачет по Физкультуре', '2024-02-14', 10, 9)
    ON CONFLICT (final_id) DO UPDATE SET
    t_name = EXCLUDED.t_name,
                                  t_date = EXCLUDED.t_date,
                                  student_id = EXCLUDED.student_id,
                                  subject_id = EXCLUDED.subject_id;

-- 5. Обновление последовательностей автоинкремента
SELECT setval(pg_get_serial_sequence('t_subject', 'subject_id'), COALESCE((SELECT MAX(subject_id) FROM t_subject), 1));
SELECT setval(pg_get_serial_sequence('t_student', 'student_id'), COALESCE((SELECT MAX(student_id) FROM t_student), 1));
SELECT setval(pg_get_serial_sequence('t_finals', 'final_id'), COALESCE((SELECT MAX(final_id) FROM t_finals), 1));

-- 6. Проверка вставки данных
DO $$
BEGIN
    RAISE NOTICE 'Данные успешно загружены:';
    RAISE NOTICE '- Предметов: %', (SELECT COUNT(*) FROM t_subject);
    RAISE NOTICE '- Студентов: %', (SELECT COUNT(*) FROM t_student);
    RAISE NOTICE '- Связей студент-предмет: %', (SELECT COUNT(*) FROM student_subject);
    RAISE NOTICE '- Финальных работ: %', (SELECT COUNT(*) FROM t_finals);
END $$;
