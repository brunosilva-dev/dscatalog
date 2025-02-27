# Documentação Java (Spring/Maven)
 
## Entities

1. Toda vez que for criar um projeto, sempre comece o desenvolvimento definindo os objetos e quando for criar as entidades, comece com as que são independentes, por exemplo categoria e produto. A categoria pode ser 1 para N, porém alguns produtos devem ser 1 para 1.

2. Dependendo do projeto a criação dos Getter/Setters, Construtor publico, Hash and Code pode ser criados todos manualmente ou utilizar a dependencia `lombok`

3. `hashCode and equals` é o método padrão que compara se um objeto igual ao outro

4. `Serializable` padrão linguagem JAVA para que o objeto possa ser convertido em sequencia de bites. É uma prática que se usava antigamente.

5. Colocar a annotation `@Entity` para mapear a tabela com o banco e a annotation `@Table(name = "tb_nomeTabela")`

6. Quando houver campo de ID devemos se formos gerar id sequencial, devemos colocar a annotation `@Id` e a `@GeneratedValue(strategy = GenerationType.IDENTITY)` que é o que gera o sequencial

## Resource

1. O resource implementa o controlador REST disponibilizando uma API.

2. Quando criar a controller, devemos inserir a annotation `@RestController`. Annotation serve para configurar uma coisa no seu código onde ele traz algo que já está implementado na class e disponibiliza seus recursos.

3. Além da annotation acima, devemos colocar também a annotation `@RequestMapping(value = "/nomeDaRota")` para que possamos definir a rota.

### EndPoints

1. São as rotas que vão retornar dados

#### ResponseEntity

1. É um objeto do spring que encapsula uma resposta HTTP onde ele é genérico e podemos definir o tipo de dado do retorno.

2. Em alguns tipos eles podem ser interface como por exemplo a `List` e nesse caso precisaremos instanciar uma classe que implementa a interface que no caso seria o `ArrayList`

```java
// Buscar todas as informações
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> list = new ArrayList<>();
		list.add(new Category(1L, "Books"));
		list.add(new Category(2L, "Eletronics"));
		return ResponseEntity.ok().body(list);
	}
```

3. `@GetMapping` é para informar que ele é um end-point

4. `List<Category> list = new ArrayList<>();` instanciar uma classe que implementa a interface que no caso seria o `ArrayList`

5. `list.add(new Category(1L, "Books"));` e `list.add(new Category(2L, "Eletronics"));` estão adicionando novas categorias

6. `return ResponseEntity.ok().body(list);` está retornando um ResponseEntity onde `.ok()` é o que vai retornar 200 caso esteja tudo certo e `.body(list)` vai retornar o corpo do objeto adicionado

## CRUD (Create, Retrieve, Update and Delete)

1. O `CRUD` são os end-points básicos quando implementamos as entidades

## Banco de Dados H2

1. Para utilizar a base de dados H2, devemos inserir a dependencia abaixo no pom:

```xml
<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<scope>runtime</scope>
</dependency>
```

## JPA/Hibernate

1. Significa Java Persistence API (JPA) padrão JAVA EE (javax.persistence) para mapeamento objeto-relacional e persistência de dados.

## Repository

1. Quando foir criar um repositorio, sempre crie como uma `Interface` e não como uma classe, pois, os recursos que serão utiliziados para comunicação com o banco é através do `extends JpaRepository<T, ID>`, exemplo:

```java
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
```

## Service

1. Criando uma camada de serviço onde ao criar devemos colocar a annotation `@Service`

2. Para que o método de serviço acesse o repositório precisamos criar dentro de service uma dependência com o repository, por exemplo `private CategoryRepository repository` e no repositório precisamos inserir a annotation `@Repository`

3. Dentro de service para ser injetado o repository la dentro, precisaremos colocar a annotation `@Autowired`, ficando da seguinte forma:

```java
@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;

	// Metod findAll
	public List<Category> findAll() {
		return repository.findAll();
	}
}
```












