/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package playground.mongo;

import playground.Person;
import io.reactivex.Single;
import io.reactivex.Flowable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sebastien Deleuze
 * @author Mark Paluch
 */
@RestController
public class PersonController {

	private final PersonCrudRepository repository;

	public PersonController(PersonCrudRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/rxjava/mongo")
	Flowable<Person> create(@RequestBody Flowable<Person> personStream) {
		return this.repository.saveAll(personStream);
	}

	@GetMapping("/rxjava/mongo")
	Flowable<Person> list() {
		return this.repository.findAll();
	}

	@GetMapping("/rxjava/mongo/{id}")
	Single<Person> findById(@PathVariable String id) {
		return this.repository.findById(id).toSingle();
	}

}
