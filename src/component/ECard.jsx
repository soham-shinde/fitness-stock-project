import React from 'react'
import { useNavigate } from 'react-router-dom'

export default function ECard({data}) {
  const navigator = new useNavigate()
  function handelClick(){
      navigator(`/equipment-store/${data.id}`)
  }
  function handelButton(e){
    e.stopPropagation()
    if(!sessionStorage.getItem('userId')){
      navigator('/login')
    }else{
      navigator(`/payment-page/${sessionStorage.getItem('userId')}/product/${data.id}`)}
  }
  return (
    <>
    <div className="e-card" title={data.name} onClick={handelClick}>
            <img src={data.image1} alt="" className="img"/>
            <div className="e-info">
                <h2>{data.name.substring(0, 50)}...</h2>
                <p>₹ {data.productPrice.toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 })}</p>
            </div>
            <button className="btn" onClick={handelButton}>Buy Now</button>
        </div>
    </>
  )
}
