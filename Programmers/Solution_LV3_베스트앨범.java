import java.util.*;
// 210430
import java.util.Map.Entry;

class Solution_LV3_베스트앨범 {
	static class Song{
		int idx, play;
		String genre;
		
		public Song(int idx, int play, String genre) {
			this.idx = idx;
			this.play = play;
			this.genre = genre;
		}

		@Override
		public String toString() {
			return "Song [idx=" + idx + ", play=" + play + ", genre=" + genre + "]";
		}
		
	}
	
	
    public int[] solution(String[] genres, int[] plays) {
    	
    	Map<String, Integer> genMap = new HashMap<String, Integer>();
    	// classic: 총 재생횟수
    	ArrayList<Song> songlist = new ArrayList<>();
    	
    	for(int i=0; i<plays.length; i++) {
    		String g = genres[i];
    		int p = plays[i];
    		songlist.add(new Song(i, p, g));
    		
    		if(genMap.containsKey(g)) {
    			genMap.put(g, genMap.get(g) + p);
    			
    		} else {
    			genMap.put(g, p);
    		}
    	}
    	
    	
    	// 1. 정렬
    	Collections.sort(songlist, new Comparator<Song>() {
    		@Override
    		public int compare(Song s1, Song s2) {
    			if(s1.genre.equals(s2.genre)) {
    				// 같은 장르라면 재생횟수 많은 순으로, idx가 작은 순으로 정렬
    				if(s1.play == s2.play) {
    					return Integer.compare(s1.idx, s2.idx);
    				}
    				return Integer.compare(s1.play, s2.play)*-1;
    			}
    			
    			// 같은 장르가 아니라면, 장르별 수록 횟수가 많은 순으로
    			else {
    				return Integer.compare(genMap.get(s1.genre), genMap.get(s2.genre))*-1;
    			}	
    		}
		});
    	
    	
    	// 2. 한 장르 당 두 개의 곡만 담는다.
    	HashMap<String, ArrayList<Integer>> bestAlbumMap = new HashMap<>();
    	
    	for(Song song: songlist) {
    		if(bestAlbumMap.containsKey(song.genre)) {
    			if(bestAlbumMap.get(song.genre).size()<2) {
    				bestAlbumMap.get(song.genre).add(song.idx);
    				
    			} else continue;
    			
    		} else {
    			bestAlbumMap.put(song.genre, new ArrayList<>());
    			bestAlbumMap.get(song.genre).add(song.idx);
    		}
    	}
    	
    	ArrayList<Integer> bestAlbum = new ArrayList<>();
    	for(Entry<String, ArrayList<Integer>> entry: bestAlbumMap.entrySet()) {
    		bestAlbum.addAll(entry.getValue());
    		System.out.print(entry.getKey() + " : ");
    		System.out.println(entry.getValue());
    	}
    	
        int[] answer = new int[bestAlbum.size()];
    	for(int i=0; i<bestAlbum.size(); i++) {
    		answer[i] = bestAlbum.get(i);
    	}

        return answer;
    }
}
