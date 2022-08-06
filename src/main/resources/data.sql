
INSERT INTO usuario (id_usuario, login, password, id_rol, activo) VALUES (1, 'Lulu', '123', 1, true);
INSERT INTO usuario (id_usuario, login, password, id_rol, activo) VALUES (2, 'imorales', '12345', 2, true);
INSERT INTO usuario (id_usuario, login, password, id_rol, activo) VALUES (3, 'lperez', '12345', 2, true);


INSERT INTO maestro (id_maestro, nombre_completo, id_usuario) VALUES (1, 'Lourdes Hdez', 1);


INSERT INTO experiencia_educativa (id_experiencia_educativa, nombre, nrc, id_maestro) VALUES (1, 'Desarrollo de aplicaciones', 12345, 1);


INSERT INTO alumno (id_alumno, nombres, apellidos, id_experiencia_educativa, id_usuario) VALUES (1, 'Ivanka', 'Morales', 1, 2);
INSERT INTO alumno (id_alumno, nombres, apellidos, id_experiencia_educativa, id_usuario) VALUES (2, 'Luis', 'Perez', 1, 3);


INSERT INTO examen (nombre, id_experiencia_educativa, momento_apertura, momento_cierre, cerrado) VALUES ('Parcial 1', 1, '2022-06-01 05:00:00', '2022-06-01 07:00:00', true);
INSERT INTO examen (nombre, id_experiencia_educativa, momento_apertura, momento_cierre, cerrado) VALUES ('Parcial 2', 1, '2022-06-15 05:00:00', '2022-06-15 08:00:00', true);
INSERT INTO examen (nombre, id_experiencia_educativa, momento_apertura, momento_cierre, cerrado) VALUES ('Ordinario', 1, '2022-07-10 05:00:00', '2022-07-10 08:00:00', false);


INSERT INTO examen_alumno (id_alumno, id_examen, momento_inicio, momento_fin, calificacion) VALUES (1, 1, '2022-07-05 14:18:01.926', '2022-07-05 14:43:23.765', 6.67);


INSERT INTO reactivo (id_experiencia_educativa, texto, opcion_1, opcion_2, opcion_3, opcion_4, opcion_correcta) VALUES (1, 'Es un framework para clientes web', 'NodeJs', 'JUnit', 'Vue', 'Pandas', 3);
INSERT INTO reactivo (id_experiencia_educativa, texto, opcion_1, opcion_2, opcion_3, opcion_4, opcion_correcta) VALUES (1, 'Qué es un requisito de software', 'Una característica', 'Una restricción', 'Una tarea', NULL, 1);
INSERT INTO reactivo (id_experiencia_educativa, texto, opcion_1, opcion_2, opcion_3, opcion_4, opcion_correcta) VALUES (1, 'Es una prueba de varios componentes', 'Unitaria', 'De caja blanca', 'De integración', '', 3);


INSERT INTO examen_reactivo (id_examen, id_reactivo, posicion) VALUES (1, 1, NULL);
INSERT INTO examen_reactivo (id_examen, id_reactivo, posicion) VALUES (1, 2, NULL);
INSERT INTO examen_reactivo (id_examen, id_reactivo, posicion) VALUES (2, 3, NULL);
INSERT INTO examen_reactivo (id_examen, id_reactivo, posicion) VALUES (2, 1, NULL);
INSERT INTO examen_reactivo (id_examen, id_reactivo, posicion) VALUES (3, 3, NULL);
INSERT INTO examen_reactivo (id_examen, id_reactivo, posicion) VALUES (1, 3, NULL);


INSERT INTO rol (nombre) VALUES ('Maestro');
INSERT INTO rol (nombre) VALUES ('Alumno');


