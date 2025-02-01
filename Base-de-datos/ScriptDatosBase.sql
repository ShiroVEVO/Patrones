insert into role values (default, "ADMIN");
insert into role values (default, "WORKER");
insert into role values (default, "CLIENT");

insert into person values (1010040526,"CC","Pedro", "Navaja", 3214584963);
insert into person values (51979655,"CC","Myke", "Towers", 3129647171);

insert into account values (default,"adrianstratos@gmail.com","perroloco69",'1999-08-10',1,1010040526,"CC");
insert into account values (default,"perreosalvaje@gmail.com","guayaguaya",'2001-10-21',1,51979655,"CC");

insert into base_cost values (default, "Basico_producción", 10000);

insert into product values (default, "Antares", 12000, 5, "La Suculenta Antares, con sus hojas largas y cubiertas de pelitos blancos, parece una estrella caída en tu hogar. Su forma en roseta evoca la majestuosidad del cosmos, añadiendo un toque celestial a cualquier espacio. Además de su belleza deslumbrante, es fácil de cuidar, perfecta para quienes buscan una planta fascinante sin complicaciones.", 1, null);
insert into product values (default, "Booney", 13000, 5, "Una suculenta en forma de roseta de tamaño considerable llegando a alcanzar los 15cm de diametro, Su color es tipicamente rosado siendo mas intenso entre más estres se le aplique a la planta.", 1, null);
insert into product values (default, "Cactus navideño rojo", 14000, 5, "El cóleo corazón, parte de uno de los mayores géneros de plantas de interior, destaca por sus vibrantes tonos negros, violetas, rosados y verdes. En esta especie, mayor exposición al sol intensifica el negro y rosado, dejando solo sutiles bordes verdes en sus hojas.", 1, null);
insert into product values (default, "Gazania amarilla rayada", 15000, 5, "Las gazanias, conocidas como \"novias de sol\", son un género de plantas florales similares al girasol, no por su tamaño, sino por su reacción a la luz. Sus flores se cierran de noche y se abren con esplendor en la mañana. Presentan una increíble variedad de colores, desde blancos hasta tonos casi negros.", 1, null);
insert into product values (default, "Gazania amarilla", 16000, 5, "La Gazania Amarilla Brillante ilumina tu jardín con sus pétalos dorados que evocan el sol. Su centro intenso resalta el color cálido, creando un espectáculo vibrante. El follaje verde profundo equilibra la flor, y su resistencia al sol y a suelos secos la hace perfecta para jardines llenos de vida.", 1, null);
insert into product values (default, "Gazania blanca rayada", 1700, 5, "La Gazania White Harmony deslumbra con sus flores blancas y rayas amoratadas, creando un contraste sofisticado y cautivador. Su vibrante follaje verde realza su luminosidad, aportando armonía al jardín. Además de su belleza, es una planta resistente, ideal para sol directo, solo requiere evitar sequías prolongadas.", 1, null);
insert into product values (default, "Gazania Blanca", 18000, 5, "La Gazania, con su centro amarillo brillante, evoca calma y serenidad, como un sol iluminando tu jardín. Sus pétalos sedosos cambian de tono con la luz del día, creando un espectáculo natural. Además, es altamente resistente y adaptable, prosperando en diversas condiciones, ya sea bajo el sol intenso o en suelos bien drenados.", 1, null);
insert into product values (default, "Gazania naranja", 19000, 5, "La Gazania Naranja Intensa destaca con sus pétalos anaranjados y centro oscuro. Sus flores se abren al sol, llenando el jardín de calidez. Su follaje verde complementa el color vibrante, y su resistencia y fácil cuidado la hacen ideal para espacios soleados y suelos bien drenados.", 1, null);
insert into product values (default, "Pink bubbles", 20000, 5, "Con hojas rechonchas y casi esféricas de color rosa suave, esta planta destaca por su hermosa apariencia. Su floración, aunque rara, es igualmente llamativa. Puede vivir tanto en interiores como exteriores, y su color rosa puede intensificarse o adquirir tonalidades violetas dependiendo del nivel de estrés.", 1, null);
insert into product values (default, "Trebol mariposa", 21000, 5, "Esta planta endémica de Brasil tiene hojas triangulares moradas que recuerdan a mariposas. Durante el día, exhibe un pompón de hojas, pero por la noche o al ser molestada, se cierra como un paraguas. Usada principalmente como planta ornamental, sus hojas y flores tienen un sabor ácido y sus rizomas son dulces, usándose como aderezo en diversas comidas.", 1, null);

insert into image values (default, "https://test-bucket-dasp.s3.us-east-2.amazonaws.com/antares.png", 1,null,null,null);
insert into image values (default, "https://test-bucket-dasp.s3.us-east-2.amazonaws.com/booney.png", 2,null,null,null);
insert into image values (default, "https://test-bucket-dasp.s3.us-east-2.amazonaws.com/cactus-navide%C3%B1o-rojo.png", 3,null,null,null);
insert into image values (default, "https://test-bucket-dasp.s3.us-east-2.amazonaws.com/gazania-amarilla-rayada.png", 4,null,null,null);
insert into image values (default, "https://test-bucket-dasp.s3.us-east-2.amazonaws.com/gazania-amarilla.png", 5,null,null,null);
insert into image values (default, "https://test-bucket-dasp.s3.us-east-2.amazonaws.com/gazania-blanca-rayada.png", 6,null,null,null);
insert into image values (default, "https://test-bucket-dasp.s3.us-east-2.amazonaws.com/gazania-blanca.png", 7,null,null,null);
insert into image values (default, "https://test-bucket-dasp.s3.us-east-2.amazonaws.com/gazania-naranja.png", 8,null,null,null);
insert into image values (default, "https://test-bucket-dasp.s3.us-east-2.amazonaws.com/pink-bubbles.png", 9,null,null,null);
insert into image values (default, "https://test-bucket-dasp.s3.us-east-2.amazonaws.com/trebol-mariposa.png", 10,null,null,null);