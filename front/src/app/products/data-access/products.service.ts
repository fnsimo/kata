import { Injectable, inject, signal } from "@angular/core";
import { Product } from "./product.model";
import { HttpClient } from "@angular/common/http";
import { catchError, Observable, of, tap } from "rxjs";

@Injectable({
    providedIn: "root"
}) export class ProductsService {
   

    private readonly http = inject(HttpClient);
    private readonly path = "/api/products";
    
    private readonly _products = signal<Product[]>([]);

    public readonly products = this._products.asReadonly();

    private readonly _productsCart = signal<Product[]>([]);

    public readonly productsCart = this._productsCart.asReadonly();

    public get(): Observable<Product[]> {
        return this.http.get<Product[]>(this.path).pipe(
            catchError((error) => {
                return this.http.get<Product[]>("assets/products.json");
            }),
            tap((products) => this._products.set(products)),
        );
    }

    public create(product: Product): Observable<boolean> {
        return this.http.post<boolean>(this.path, product).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => [product, ...products])),
        );
    }

    public update2(product: Product): Observable<boolean> {
        return this.http.patch<boolean>(`${this.path}/${product.id}`, product).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => {
                return products.map(p => p.id === product.id ? product : p)
            })),
        );
    }

    public update(product: Product): Observable<boolean> {
        return this.http.patch<boolean>(`${this.path}/${product.id}`, product).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => 
            {
                this._products.update(products => {
                    return products.map(p => p.id === product.id ? product : p)
                });

                this._productsCart.update(products => {
                    return products.map(p => p.id === product.id ? product : p)
                });
            }
               
        
        ),
        );
    }

    public delete2(productId: number): Observable<boolean> {
        return this.http.delete<boolean>(`${this.path}/${productId}`).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => products.filter(product => product.id !== productId))),
        );
    }

    public delete(productId: number): Observable<boolean> {
        return this.http.delete<boolean>(`${this.path}/${productId}`).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() =>
            {
                this._products.update(products => products.filter(product => product.id !== productId));
                this._productsCart.update(products => products.filter(product => product.id !== productId));
                
            }
                
            
            ),
        );
    }

    public addToCart(product: Product) {

        this._productsCart.update(products => [product, ...products]);


    }


    



    
      



    removeFromCart(product: Product) {
        this._productsCart.update((products) => {
            const index = products.findIndex((p) => p.id === product.id);
            if (index !== -1) {
                products.splice(index, 1); 
            }
            return products;
        });
    }
    
}