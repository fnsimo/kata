import {
  Component,
  inject,
  ViewChild,
} from "@angular/core";
import { RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import { ProductsService } from "./products/data-access/products.service";
import { Product } from "./products/data-access/product.model";

import { OverlayPanel } from 'primeng/overlaypanel';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { ToastModule } from 'primeng/toast';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: "app-root",
  templateUrl: "./app.component copy.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent,OverlayPanelModule, ToastModule, 
    TableModule, ButtonModule],
})
export class AppComponent {
  title = "ALTEN SHOP";


  private readonly productsService = inject(ProductsService);

  public readonly productsCart = this.productsService.productsCart;

  @ViewChild('op') overlayPanel: OverlayPanel | undefined; 

  get cartItemCount(): number {
    return this.productsCart().length; 
  }



  

  removeFromCart(product: Product) {

    this.productsService.removeFromCart(product);
    this.productsService.update(product).subscribe();

    if(this.productsCart().length<1){
  
      this.overlayPanel!.hide();

    }

  }

  calculateTotalPrice(): number {
    return this.productsCart().reduce((total, product) => total + (product.price), 0);
  }

  getGroupedProductss() {
    const grouped: any[] = [];
  
    this.productsCart().forEach((product: any) => {
      const existingProduct = grouped.find((p) => p.product.id === product.id);
      if (existingProduct) {
        existingProduct.quantity++;
      } else {
        grouped.push({ product, quantity: 1 });
      }
    });
  
    return grouped;
  }
  

  getGroupedProducts() {
    const grouped = new Map<number, { product: Product; quantity: number }>();
  
    this.productsCart().forEach((product) => {
      if (grouped.has(product.id)) {
        grouped.get(product.id)!.quantity++;
      } else {
        grouped.set(product.id, { product, quantity: 1 });
      }
    });

  
    return Array.from(grouped.values());
  }
  
    
}
