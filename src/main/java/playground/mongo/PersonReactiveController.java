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

import org.reactivestreams.Publisher;
import playground.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
public class PersonReactiveController {

	private final PersonReactiveCrudRepository repository;

	public PersonReactiveController(PersonReactiveCrudRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/mongo")
	Mono<Void> create(@RequestBody Publisher<Person> personStream) {
		return this.repository.saveAll(personStream).then();
	}

	@GetMapping("/mongo")
	Flux<Person> list() {
		return this.repository.findAll();
	}

	@GetMapping("/mongo/{id}")
	Mono<Person> findById(@PathVariable String id) {
		return this.repository.findById(id);
	}

}
