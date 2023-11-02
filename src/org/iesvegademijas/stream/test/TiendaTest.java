package org.iesvegademijas.stream.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import org.iesvegademijas.hibernate.Fabricante;
import org.iesvegademijas.hibernate.FabricanteHome;
import org.iesvegademijas.hibernate.Producto;
import org.iesvegademijas.hibernate.ProductoHome;
import org.junit.jupiter.api.Test;
import static java.util.Collections.*;

class TiendaTest {
	
	@Test
	void testSkeletonFrabricante() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();

			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	

	@Test
	void testSkeletonProducto() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	@Test
	void testAllFabricante() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
			assertEquals(9,listFab.size());
		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	@Test
	void testAllProducto() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
			assertEquals(11,listProd.size());
		
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		

	
	}
	
	/**
	 * 1. Lista los nombres y los precios de todos los productos de la tabla producto
	 */
	@Test
	void test1() {
	
		ProductoHome prodHome = new ProductoHome();
		
		try {
			prodHome.beginTransaction();
			
			List<Producto> listProd = prodHome.findAll();
			

			List<String>listanombrePrecio=listProd.stream().map(producto -> "Nombre: "+producto.getNombre()+"  Precio: "+ producto.getPrecio()).collect(toList());
			listanombrePrecio.forEach(s -> System.out.println(s));
			prodHome.commitTransaction();

		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	
	}
	
	
	/**
	 * 2. Devuelve una lista de Producto completa con el precio de euros convertido a dólares .
	 */
	@Test
	void test2() {
		
		ProductoHome prodHome = new ProductoHome();
		
		try {
			prodHome.beginTransaction();			
			List<Producto> listProd = prodHome.findAll();
			

			List<String>listaprecioProductos=
					listProd.stream().map(producto -> ("Nombre: "+producto.getNombre()+"Precio "+BigDecimal.valueOf(producto.getPrecio()*0.95).setScale(2, RoundingMode.HALF_UP))).collect(toList());
			listaprecioProductos.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 3. Lista los nombres y los precios de todos los productos, convirtiendo los nombres a mayúscula.
	 */
	@Test
	void test3() {
		
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						

			List<String>listaNombreprecioProductos=
					listProd.stream().map(producto -> ("Nombre: "+producto.getNombre().toUpperCase()+"Precio "+BigDecimal.valueOf(producto.getPrecio()*0.95).setScale(2, RoundingMode.HALF_UP))).collect(toList());
			listaNombreprecioProductos.forEach(System.out::println);
			prodHome.commitTransaction();

		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 4. Lista el nombre de todos los fabricantes y a continuación en mayúsculas los dos primeros caracteres del nombre del fabricante.
	 */
	@Test
	void test4() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();

			List<String>listaprecioNombre2caracteresMayus=
					listFab.stream().map(fabricante -> ("Nombre: "+fabricante.getNombre()+fabricante.getNombre().substring(0,2).toUpperCase())).collect(toList());
			listaprecioNombre2caracteresMayus.forEach(System.out::println);

			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 5. Lista el código de los fabricantes que tienen productos.
	 */
	@Test
	void test5() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					

			List<String>listaFabricantes=
					listFab.stream()
							.filter(fabricante -> !(fabricante.getProductos().isEmpty()))
							.map(fabricante -> ("Nombre: "+fabricante.getNombre())+" Codigo: "+fabricante.getCodigo()).collect(toList());
			listaFabricantes.forEach(System.out::println);

			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 6. Lista los nombres de los fabricantes ordenados de forma descendente.
	 */
	@Test
	void test6() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					

			List<String>listaFabricantesordenados=
					listFab.stream()
							.sorted(comparing(Fabricante::getNombre))
							.map(fabricante -> ("Nombre: "+fabricante.getNombre())).collect(toList());
			listaFabricantesordenados.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 7. Lista los nombres de los productos ordenados en primer lugar por el nombre de forma ascendente y en segundo lugar por el precio de forma descendente.
	 */
	@Test
	void test7() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						

			List<String>listaFabricantesordenados=
					listProd.stream()
							.sorted(comparing(Producto::getNombre).thenComparing(comparing(Producto::getPrecio).reversed()))
							.map(producto -> "Nombre: "+producto.getNombre()+" Precio: "+ producto.getPrecio()).collect(toList());
							listProd.forEach(System.out::println);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 8. Devuelve una lista con los 5 primeros fabricantes.
	 */
	@Test
	void test8() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					

			List<String>primerosFabricantes=listFab.stream()

					.sorted(comparing(Fabricante::getCodigo))
					.limit(5)
					.map(fabricante -> fabricante.toString())
					.collect(toList());
			primerosFabricantes.forEach(System.out::println);

			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 9.Devuelve una lista con 2 fabricantes a partir del cuarto fabricante. El cuarto fabricante también se debe incluir en la respuesta.
	 */
	@Test
	void test9() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();

			List<String>listaRecortada=listFab.stream()
					.sorted(comparing(Fabricante::getCodigo))
					.skip(4)
					.limit(2)
					.map(fabricante -> "Nombre: "+fabricante.getNombre()+" Codigo: "+fabricante.getCodigo()).collect(toList());
					listaRecortada.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 10. Lista el nombre y el precio del producto más barato
	 */
	@Test
	void test10() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						

			List<String>productomasbarato=listProd.stream()
					.sorted(comparing(Producto::getPrecio))
					.limit(1)
							.map(producto -> "Nombre"+producto.getNombre()+" Precio: "+producto.getPrecio()).collect(toList());
			productomasbarato.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 11. Lista el nombre y el precio del producto más caro
	 */
	@Test
	void test11() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						

			List<String>productomascaro=listProd.stream()
					.sorted(comparing(Producto::getPrecio).reversed())
					.limit(1)
					.map(producto -> "Nombre"+producto.getNombre()+" Precio: "+producto.getPrecio()).collect(toList());
			productomascaro.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 12. Lista el nombre de todos los productos del fabricante cuyo código de fabricante es igual a 2.
	 * 
	 */
	@Test
	void test12() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						

			List<String> ProductoFabricante2=listProd.stream()
					.filter(producto -> producto.getFabricante().getCodigo()==2)

							.map(producto -> "Nombre: "+producto.getNombre()).collect(toList());
					ProductoFabricante2.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 13. Lista el nombre de los productos que tienen un precio menor o igual a 120€.
	 */
	@Test
	void test13() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						

				List<String>listproductosmenoroiguala120=listProd.stream()
						.filter(producto -> producto.getPrecio()<=120)
						.map(producto -> "Nombre: "+producto.getNombre()).collect(toList());
				listproductosmenoroiguala120.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 14. Lista los productos que tienen un precio mayor o igual a 400€.
	 */
	@Test
	void test14() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						

			List<String>listproductosmayoroigual400=listProd.stream()
					.filter(producto -> producto.getPrecio()>=400)
					.map(producto -> "Nombre: "+producto.getNombre()).collect(toList());
			listproductosmayoroigual400.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 15. Lista todos los productos que tengan un precio entre 80€ y 300€. 
	 */
	@Test
	void test15() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();	
			

				List<String>productosentre80y300=listProd.stream()
						.filter(producto -> producto.getPrecio()>80 && producto.getPrecio()<300)
								.map(producto -> "Nombre: "+producto.getNombre()+"Precio: "+producto.getPrecio()).collect(toList());
				productosentre80y300.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 16. Lista todos los productos que tengan un precio mayor que 200€ y que el código de fabricante sea igual a 6.
	 */
	@Test
	void test16() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						

				List<String>listmayor200codigo6=listProd.stream()
						.filter(producto -> producto.getPrecio()>200 && producto.getFabricante().getCodigo()==6)
								.map(producto ->"Nombre: "+ producto.getNombre()+"Codigo fabricante: "+ producto.getFabricante().getCodigo()).collect(toList());
				listmayor200codigo6.forEach(System.out::println);
				prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 17. Lista todos los productos donde el código de fabricante sea 1, 3 o 5 utilizando un Set de codigos de fabricantes para filtrar.
	 */
	@Test
	void test17() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
			

			final Set<Integer>codigos = new HashSet<>();
			codigos.add(1);
			codigos.add(3);
			codigos.add(5);

			Set<String> listadefabricante135=listProd.stream()
					.filter(producto -> codigos.contains(producto.getFabricante().getCodigo()))
					.distinct()
					.map(producto -> "Nombre: "+producto.getNombre()+"Codigo Fabricante: "+producto.getFabricante().getCodigo()).collect(toSet());
			listadefabricante135.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 18. Lista el nombre y el precio de los productos en céntimos.
	 */
	@Test
	void test18() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
			

			List<String>productoPrecioacentimo=listProd.stream()
							.map(producto -> "Nombre: "+producto.getNombre()+ "Precio: "+BigDecimal.valueOf(producto.getPrecio()*100)).collect(toList());
			listProd.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	
	/**
	 * 19. Lista los nombres de los fabricantes cuyo nombre empiece por la letra S
	 */
	@Test
	void test19() {
	
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					

			List<String>fabricantes =listFab.stream()
					.filter(fabricante -> fabricante.getNombre().substring(0,1).toUpperCase().equals("S"))
							.map(fabricante -> "Nombre: "+fabricante.getNombre()).collect(toList());
			fabricantes.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 20. Devuelve una lista con los productos que contienen la cadena Portátil en el nombre.
	 */
	@Test
	void test20() {
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
			List<Producto> listProd = prodHome.findAll();
			
			List<String>listaPortatiles =
					listProd.stream()
							.filter(producto -> producto.getNombre().contains("Portátil"))
							.map(producto -> "Codigo: "+producto.getCodigo()+"Nombre"+producto.getNombre()).collect(toList());
						listaPortatiles.forEach(System.out::println);
			//List<String>portailes
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 21. Devuelve una lista con el nombre de todos los productos que contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.
	 */
	@Test
	void test21() {
	
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			List<String>listaMonitores=listProd.stream()
					.filter(producto -> producto.getNombre().contains("Monitor") && producto.getPrecio()<215)
					.map(producto -> "Codigo: "+producto.getCodigo()+"Nombre"+producto.getNombre()).collect(toList());
			listaMonitores.forEach(System.out::println);
			//TODO STREAMS
				
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 22. Lista el nombre y el precio de todos los productos que tengan un precio mayor o igual a 180€. 
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre (en orden ascendente).
	 */
	@Test
	void test22() {
		
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			List<String>listaOrdenada=listProd.stream()
					.filter(producto -> producto.getPrecio()>=180)
					.sorted(Comparator.comparing( Producto::getPrecio).reversed().thenComparing(comparing(Producto::getNombre)))
					.map(producto -> "Nombre: "+producto.getNombre()+"Precio: "+producto.getPrecio())
							.collect(toList());
			listaOrdenada.forEach(System.out::println);
			//TODO STREAMS
				
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 23. Devuelve una lista con el nombre del producto, precio y nombre de fabricante de todos los productos de la base de datos. 
	 * Ordene el resultado por el nombre del fabricante, por orden alfabético.
	 */
	@Test
	void test23() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			List<String> listaOrdenada=listProd.stream()
					.sorted(Comparator.comparing(producto -> producto.getFabricante().getNombre()))
					.map(producto -> "Nombre producto:"+producto.getNombre()+"Precio: "+producto.getPrecio()+"Nombre fabricante: "+producto.getFabricante().getNombre()).collect(toList());
					listaOrdenada.forEach(System.out::println);
			//TODO STREAMS
			
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 24. Devuelve el nombre del producto, su precio y el nombre de su fabricante, del producto más caro.
	 */
	@Test
	void test24() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS
			List<String> productomasCaro=listProd.stream()
					.sorted(Comparator.comparing(Producto::getPrecio).reversed())
					.limit(1)
							.map(producto -> "Nombre: "+producto.getNombre()+"Precio: "+ producto.getPrecio()+"Nombre fabricante: "+producto.getFabricante().getNombre()).collect(toList());
			productomasCaro.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 25. Devuelve una lista de todos los productos del fabricante Crucial que tengan un precio mayor que 200€.
	 */
	@Test
	void test25() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS
			List<String>Crucial=listProd.stream()
					.filter(producto -> producto.getFabricante().getNombre().equalsIgnoreCase("Crucial") )
					.filter(producto ->  producto.getPrecio()>200)
							.map(producto -> "Nombre"+producto.getNombre()+"Precio: "+ producto.getPrecio()).collect(toList());
			Crucial.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 26. Devuelve un listado con todos los productos de los fabricantes Asus, Hewlett-Packard y Seagate
	 */
	@Test
	void test26() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS
			List<String>listaFabricantedeMarcas=listProd.stream()
					.filter(producto -> producto.getNombre().equalsIgnoreCase("Asus")||producto.getNombre().equalsIgnoreCase("Hewlett-Packard") || producto.getNombre().equalsIgnoreCase("Seagate"))
					.map(producto -> "Nombre producto: "+producto.getNombre()+"Fabricante: "+producto.getFabricante())
					.collect(toList());
			listaFabricantedeMarcas.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 27. Devuelve un listado con el nombre de producto, precio y nombre de fabricante, de todos los productos que tengan un precio mayor o igual a 180€. 
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre.
	 * El listado debe mostrarse en formato tabla. Para ello, procesa las longitudes máximas de los diferentes campos a presentar y compensa mediante la inclusión de espacios en blanco.
	 * La salida debe quedar tabulada como sigue:

Producto                Precio             Fabricante
-----------------------------------------------------
GeForce GTX 1080 Xtreme|611.5500000000001 |Crucial
Portátil Yoga 520      |452.79            |Lenovo
Portátil Ideapd 320    |359.64000000000004|Lenovo
Monitor 27 LED Full HD |199.25190000000003|Asus

	 */		
	@Test
	void test27() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			List<String> listaOrdenada=listProd.stream()
					.sorted(Comparator.comparing(Producto::getPrecio).reversed().thenComparing(comparing(Producto::getNombre)))
					.filter(producto -> producto.getPrecio()>180)
					.map(producto -> " "+producto.getNombre()+"  \t | "+producto.getPrecio()+" | \t "+ producto.getFabricante().getNombre()).collect(toList());
			System.out.println("Producto            \t  Precio             Fabricante \n -----------------------------------------------------");
			//TODO STREAMS
			listaOrdenada.forEach(System.out::println);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 28. Devuelve un listado de los nombres fabricantes que existen en la base de datos, junto con los nombres productos que tiene cada uno de ellos. 
	 * El listado deberá mostrar también aquellos fabricantes que no tienen productos asociados. 
	 * SÓLO SE PUEDEN UTILIZAR STREAM, NO PUEDE HABER BUCLES
	 * La salida debe queda como sigue:
Fabricante: Asus

            	Productos:
            	Monitor 27 LED Full HD
            	Monitor 24 LED Full HD

Fabricante: Lenovo

            	Productos:
            	Portátil Ideapd 320
            	Portátil Yoga 520

Fabricante: Hewlett-Packard

            	Productos:
            	Impresora HP Deskjet 3720
            	Impresora HP Laserjet Pro M26nw

Fabricante: Samsung

            	Productos:
            	Disco SSD 1 TB

Fabricante: Seagate

            	Productos:
            	Disco duro SATA3 1TB

Fabricante: Crucial

            	Productos:
            	GeForce GTX 1080 Xtreme
            	Memoria RAM DDR4 8GB

Fabricante: Gigabyte

            	Productos:
            	GeForce GTX 1050Ti

Fabricante: Huawei

            	Productos:


Fabricante: Xiaomi

            	Productos:

	 */
	@Test
	void test28() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
			List<String>listaFabricantesProducto=listFab.stream()
					.map(fabricante -> "Fabricante: \n "+fabricante.getNombre()+"\n "+fabricante.getProductos().stream().map(Producto::getNombre).collect(toList()).toString().replace('[',' ').replace(']',' '))
							.collect(toList());
			listaFabricantesProducto.forEach(System.out::println);
			//TODO STREAMS
								
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 29. Devuelve un listado donde sólo aparezcan aquellos fabricantes que no tienen ningún producto asociado.
	 */
	@Test
	void test29() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
			List<String>listFabricantesnocompran=listFab.stream()
					.filter(fabricante -> fabricante.getProductos().isEmpty())
							.map(fabricante -> "Nombre fabricante: "+fabricante.getNombre()).collect(toList());
			listFabricantesnocompran.forEach(System.out::println);
			//TODO STREAMS
								
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 30. Calcula el número total de productos que hay en la tabla productos. Utiliza la api de stream.
	 */
	@Test
	void test30() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			Long numeroproductos=listProd.stream().count();
			System.out.println(numeroproductos);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}

	
	/**
	 * 31. Calcula el número de fabricantes con productos, utilizando un stream de Productos.
	 */
	@Test
	void test31() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
			Stream productosConFabricantes=listProd.stream()
					.map(producto -> producto.getFabricante())
					.distinct();
			long numfabricantes=productosConFabricantes.count();
			System.out.println(numfabricantes);
			//TODO STREAMS
			
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 32. Calcula la media del precio de todos los productos
	 */
	@Test
	void test32() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();

			final long numeroproductos=listProd.stream().count();
			Optional<Double> precios=listProd.stream().map(producto -> producto.getPrecio()).reduce((a, b) -> (a + b));
			BigDecimal media=BigDecimal.valueOf( precios.get()/numeroproductos);
			System.out.println(media.setScale(2,RoundingMode.HALF_UP));
			//TODO STREAMS
			
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 33. Calcula el precio más barato de todos los productos. No se puede utilizar ordenación de stream.
	 */
	@Test
	void test33() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			Optional<Double>productobarato=listProd.stream()
					.map(producto -> producto.getPrecio())
					.reduce(Double::min)
					;
			System.out.println("Producto mas barato"+productobarato.get());
					prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 34. Calcula la suma de los precios de todos los productos.
	 */
	@Test
	void test34() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			Optional<Double> precios=listProd.stream().map(producto -> producto.getPrecio()).reduce((a, b) -> (a + b));
			//TODO STREAMS
			System.out.println("Suma de precios: "+precios.get());
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 35. Calcula el número de productos que tiene el fabricante Asus.
	 */
	@Test
	void test35() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			Long numerodeAsus=listProd
					.stream()
					.filter(producto -> producto.getFabricante().getNombre().equals("Asus"))
					.count();
			System.out.println(numerodeAsus);
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 36. Calcula la media del precio de todos los productos del fabricante Asus.
	 */
	@Test
	void test36() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			Long numerodeAsus=listProd
					.stream()
					.filter(producto -> producto.getFabricante().getNombre().equals("Asus"))
					.count();
			Optional<Double> sumaAsus=listProd
					.stream()
					.filter(producto -> producto.getFabricante().getNombre().equals("Asus"))
					.map(producto -> producto.getPrecio())
					.reduce(Double::sum);
			BigDecimal media=BigDecimal.valueOf(sumaAsus.get()/numerodeAsus);
			System.out.println("La  media es: "+media);
			//TODO STREAMS
			
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	
	/**
	 * 37. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos que tiene el fabricante Crucial. 
	 *  Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
	 */
	@Test
	void test37() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
						
			//TODO STREAMS


			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 38. Muestra el número total de productos que tiene cada uno de los fabricantes. 
	 * El listado también debe incluir los fabricantes que no tienen ningún producto. 
	 * El resultado mostrará dos columnas, una con el nombre del fabricante y otra con el número de productos que tiene. 
	 * Ordene el resultado descendentemente por el número de productos. Utiliza String.format para la alineación de los nombres y las cantidades.
	 * La salida debe queda como sigue:
	 
		 Fabricante     #Productos
	-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
			   Asus              2
			 Lenovo              2
	Hewlett-Packard              2
			Samsung              1
			Seagate              1
			Crucial              2
		   Gigabyte              1
			 Huawei              0
			 Xiaomi              0

	 */
	@Test
	void test38() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();

			//TODO STREAMS
			List<String>numerodeProductosFabricante=listFab
					.stream()
			.map(fabricante -> fabricante.getNombre()+"\t"+fabricante.getProductos().stream()
					.filter(producto ->producto.getFabricante().getNombre().equals(fabricante.getNombre()) )
					.count())
			.collect(toList());
			System.out.println(" Fabricante     #Productos");
			System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
			numerodeProductosFabricante.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 39. Muestra el precio máximo, precio mínimo y precio medio de los productos de cada uno de los fabricantes. 
	 * El resultado mostrará el nombre del fabricante junto con los datos que se solicitan. Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
	 * Deben aparecer los fabricantes que no tienen productos.
	 */
	@Test
	void test39() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 40. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos de los fabricantes que tienen un precio medio superior a 200€. 
	 * No es necesario mostrar el nombre del fabricante, con el código del fabricante es suficiente.
	 */
	@Test
	void test40() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 41. Devuelve un listado con los nombres de los fabricantes que tienen 2 o más productos.
	 */
	@Test
	void test41() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
			List<String> fabricantesconproductos=listFab.stream()
					.filter(fabricante -> fabricante.getProductos().stream().count()>2)
					.map(fabricante -> fabricante.getNombre())
					.collect(toList());
			fabricantesconproductos.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 42. Devuelve un listado con los nombres de los fabricantes y el número de productos que tiene cada uno con un precio superior o igual a 220 €. 
	 * Ordenado de mayor a menor número de productos.
	 */
	@Test
	void test42() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
			List<String> fabricantesconproductos=listFab.stream()
					.map(fabricante -> fabricante.getNombre()+" : "+fabricante.getProductos()
							.stream()
							.filter(producto -> producto.getPrecio()>=220).count())
					.collect(toList());
			//TODO STREAMS
			fabricantesconproductos.forEach(System.out::println);
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 43.Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
	 */
	@Test
	void test43() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();

			//TODO STREAMS
			/*List<String>listPrecios=listFab.stream()
					.filter(fabricante -> fabricante.getProductos().stream().collect(groupingBy(Producto::getPrecio,summarizingDouble(Producto::getPrecio)))

									.collect(toList());*/
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 44. Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
	 * Ordenado de menor a mayor por cuantía de precio de los productos.
	 */
	@Test
	void test44() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 45. Devuelve un listado con el nombre del producto más caro que tiene cada fabricante. 
	 * El resultado debe tener tres columnas: nombre del producto, precio y nombre del fabricante. 
	 * El resultado tiene que estar ordenado alfabéticamente de menor a mayor por el nombre del fabricante.
	 */
	@Test
	void test45() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
			
	}
	
	/**
	 * 46. Devuelve un listado de todos los productos que tienen un precio mayor o igual a la media de todos los productos de su mismo fabricante.
	 * Se ordenará por fabricante en orden alfabético ascendente y los productos de cada fabricante tendrán que estar ordenados por precio descendente.
	 */
	@Test
	void test46() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS															
		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
			
	}
	
}

