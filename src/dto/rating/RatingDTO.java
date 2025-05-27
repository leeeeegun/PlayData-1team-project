package dto.rating;

public class RatingDTO {
//	id, lecture_id, rating, created_at
	//id, lecture_id, rating, created_at
		private int id;
		private int lecture_id;
		private float rating;
		
		public RatingDTO() {
			
		}

		public RatingDTO(int id, int lecture_id, float rating) {
			super();
			this.id = id;
			this.lecture_id = lecture_id;
			this.rating = rating;
		}

		public RatingDTO(int lecture_id, float rating) {
			super();
			this.lecture_id = lecture_id;
			this.rating = rating;
		}

		@Override
		public String toString() {
			return "PaymentDTO [id=" + id + ", lecture_id=" + lecture_id + ", rating=" + rating + "]";
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getLecture_id() {
			return lecture_id;
		}

		public void setLecture_id(int lecture_id) {
			this.lecture_id = lecture_id;
		}

		public float getRating() {
			return rating;
		}

		public void setRating(float rating) {
			this.rating = rating;
		}
}
