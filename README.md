# Программа для контроля ресурсов и работ на самолетах типа МиГ-29 СМТ.
## Описание:
Программа предназначена для использования инженерно-техническим составом.
Предоставляет возможность централизованного хранения данных о воздушных судах, инженерном составе и агрегатах,
контролировать остатки ресурсов самолета и отдельных агрегатов до работ по обслуживанию и ремонту,
производить автоматический пересчет наработки и уведомлять пользователя о приближающихся работах.
## Функционал:
* добавление/изменение/удаление информации об инженерах, самолетах и агрегатах
* установка связей "агрегат-самолет", "инженер-самолет"
* на основании введенных данных пересчет наработки для каждого конкретного агрегата
* система индикации, которая сигнализирует о приближении работ на самолете или отдельном агрегате
* загрузка/сохранение пользовательских данных с/на локальный компьютер пользователя
## Используемые инструменты и технологии:
* Java 8+
* JavaFX
* Maven
* Jackson
* Slf4j
* Lombok
