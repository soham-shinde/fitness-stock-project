import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { getStoreEquipmentById } from '../api-client/api-module';
import Loader from '../component/Loader';
import Footer from '../component/Footer';
import NavBar from '../component/NavBar';

export default function EquipmentDetailPage({type1}) {

  const { equipmentId } = useParams();
  const [productItem, setProductItem] = useState(null)
  const [loading, setLoading] = useState(true);
  const navigator = new useNavigate();

  useEffect(() => {
    fetchData();
  }, []);

  useEffect(() => {
    if (productItem) {
      const imgSub = document.querySelectorAll(".sub-img img");
      const imgMain1 = document.getElementById("main-img1");
      const imgMain2 = document.getElementById("main-img2");

      imgSub.forEach((item, index) => {
        item.addEventListener("mouseover", function () {
          if (index % 2 !== 0) {
            imgMain1.src = item.src;
          }
          else {
            imgMain2.src = item.src
          }
        });
      });
    }
  }, [productItem]);

  const fetchData = async () => {
    try {
      console.log(equipmentId);
      const item = await getStoreEquipmentById(equipmentId);
      setProductItem(item);
      setLoading(false); 


    } catch (error) {
      console.error('Error fetching products:', error);
    }
  };

  function handelButton(e){
    e.stopPropagation()
    if(!sessionStorage.getItem('userId')){
      navigator('/login')
    }else{
      navigator(`/payment-page/${sessionStorage.getItem('userId')}/product/${productItem.id}`)}
  }

  return (
    <><NavBar user={type1}/>
    {loading?<Loader/>:
      <div className="body-container">
        <div className="store-container">
          <div className="imgage-container">
            <div className="main-img">
              <img src={productItem.image1} alt="" id="main-img2" />
              <img src={productItem.image3} alt="" id="main-img1" />
            </div>
            <div className="sub-img">
              <img src={productItem.image1} alt="" />
              <img src={productItem.image2} alt="" />
              <img src={productItem.image3} alt="" />
              <img src={productItem.image4} alt="" />
            </div>
          </div>
          <div className="info-container-1">
            <div className="product-name">{productItem.name}</div>
            <div className="divider-y"></div>
            <div className="price-container">
              <h3>Price : </h3> ₹ {productItem.productPrice.toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 })}
            </div>
            <div className="btn-container">
             {!type1&& <button className="btn" onClick={handelButton}>Buy Now</button>}
            </div>
            <div className="product-description">
              <h2>Product Details :</h2>
              <p dangerouslySetInnerHTML={{ __html: productItem.description.replace(/\n/g, '<br>') }}></p>
            </div>
          </div>
        </div>
      </div>
      }
      <Footer/>
    </>
  )
}
