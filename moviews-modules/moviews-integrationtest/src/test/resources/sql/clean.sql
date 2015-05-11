SET @savedMovieId=(SELECT movieId FROM movie WHERE originalTitle='movieTestMovie')
DELETE FROM cast_and_crew_in_movie where movieId=@savedMovieId
DELETE cac FROM cast_and_crew_member cac left join cast_and_crew_in_movie cacim on cac.castAndCrewMemberId=cacim.castAndCrewMemberId where cacim.castAndCrewMemberId is null
DELETE FROM movie where movieId=@savedMovieId